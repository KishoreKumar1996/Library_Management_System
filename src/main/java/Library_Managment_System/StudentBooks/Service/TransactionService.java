package Library_Managment_System.StudentBooks.Service;


import Library_Managment_System.StudentBooks.DTOs.IssuedBookRequestDTO;
import Library_Managment_System.StudentBooks.DTOs.ReturnBookRequestDTO;
import Library_Managment_System.StudentBooks.Enums.CardStatus;
import Library_Managment_System.StudentBooks.Enums.TransactionStatus;
import Library_Managment_System.StudentBooks.Enums.TransactionType;
import Library_Managment_System.StudentBooks.Models.Book;
import Library_Managment_System.StudentBooks.Models.Card;
import Library_Managment_System.StudentBooks.Models.Transaction;
import Library_Managment_System.StudentBooks.Repo.BookRepository;
import Library_Managment_System.StudentBooks.Repo.CardRepository;
import Library_Managment_System.StudentBooks.Repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssuedBookRequestDTO issuedBookRequestDTO) throws Exception {
        //extract values from DTO object
        int bookId = issuedBookRequestDTO.getBookId();
        int cardId = issuedBookRequestDTO.getCardId();

        //get book and card entity with resp ids... to save it in transaction repo
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        //repo deals with entity, so create object
        Transaction transaction = new Transaction();

        //set basic attributes of entity layers
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.ISSUE);

        //save transaction record even if status is not success -> eg: money transactions are recorded, GPAY
        //mandatory validation or checks

        //1. book doesnt exist, or book is already issued
        if (book == null || book.isIssued() == true) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }

        //2. card is not valid
        if (card == null || !card.getCardStatus().equals(CardStatus.ACTIVATED)) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }

        //3. Card has reached max potential
        List<Book> booksIssued = card.getBooksIssued();
        if(booksIssued.size() == 5) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card has reached max potential. \n Return a book, before issuing a new one");
        }

        //transaction success -> save
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //set attributes of book
        //1. set book as issued
        book.setIssued(true);

        //2. update list of transactions made on this book
        List<Transaction> listOfTransactionsMadeOnBook = book.getListOfTransactions();
        listOfTransactionsMadeOnBook.add(transaction);
        book.setListOfTransactions(listOfTransactionsMadeOnBook);

        //set attributes of card
        //1. update list of books issued by this card... Book and Card
        List<Book> issuedBooksByCard = card.getBooksIssued(); //get
        issuedBooksByCard.add(book);                          //add
        card.setBooksIssued(issuedBooksByCard);               //save or update

        //2. update list of transactions made by this card... Card and Transaction
        List<Transaction> transactionListForCard = card.getTransactionList(); //get
        transactionListForCard.add(transaction);                              //add
        card.setTransactionList(transactionListForCard);                      //save or update

        //update
        //only card -> parent of both book, transaction, so saved by cascading
        cardRepository.save(card);

        return "transaction recorded";
    }

    public String returnBook(ReturnBookRequestDTO returnBookRequestDTO) throws Exception {
        //extract values from DTO object
        int bookId = returnBookRequestDTO.getBookId();
        int cardId = returnBookRequestDTO.getCardId();

        //get book and card entity with resp ids... to save it in transaction repo
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        //repo deals with entity, so create object
        Transaction transaction = new Transaction();

        //set basic attributes of entity layers
        transaction.setBook(book);
        transaction.setCard(card);
        transaction.setTransactionType(TransactionType.RETURN);

        //save transaction record even if status is not success -> eg: money transactions are recorded, GPAY
        //mandatory validation or checks

        //1. book doesnt exist
        if (book == null) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not available");
        }

        //2. book isnt issued, cant be returned
        if (!book.isIssued() == true) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Book is not issued, so cant be returned");
        }

        //3. card is not valid
        if (card == null || !card.getCardStatus().equals(CardStatus.ACTIVATED)) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new Exception("Card is not valid");
        }

        //transaction success -> save
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);

        //calculate fine
        //fine charged after 2 weeks (14 days), @ Rs.10 / day
        List<Transaction> transactions = transactionRepository.findTransaction
                (cardId, bookId, TransactionType.ISSUE, TransactionStatus.SUCCESS);
        Transaction returnTransaction = transactions.get(transactions.size() - 1);

        Date issueDate = returnTransaction.getTransactionDate();
        Date returnDate = new Date();

        long days = findDifference(issueDate, returnDate);
        double fine = 0;

        if(days > 10) {
            fine = 10 * (days - 10);
        }

        //set attributes of book
        //1. set book as issued
        book.setIssued(false);

        //2. update list of transactions made on this book
        List<Transaction> listOfTransactionsMadeOnBook = book.getListOfTransactions();
        listOfTransactionsMadeOnBook.add(transaction);
        book.setListOfTransactions(listOfTransactionsMadeOnBook);

        //3. set fine
        transaction.setFine((int)fine);

        //set attributes of card
        //update list of transactions made by this card... Card and Transaction
        List<Transaction> transactionListForCard = card.getTransactionList(); //get
        transactionListForCard.add(transaction);                              //add
        card.setTransactionList(transactionListForCard);                      //save or update

        //update
        //only card -> parent of both book, transaction, so saved by cascading
        cardRepository.save(card);

        return "transaction recorded";
    }

    static long findDifference(Date issueDate, Date returnDate) {
        long difference_In_Time = returnDate.getTime() - issueDate.getTime();
        long difference_In_Seconds = (difference_In_Time / 1000) % 60;
        long difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;
        long difference_In_Hours = (difference_In_Time / (1000 * 60 * 60)) % 24;
        long difference_In_Years = (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
        long difference_In_Days = (difference_In_Time / (1000 * 60 * 60 * 24)) % 365;

        return difference_In_Days;
    }
}
