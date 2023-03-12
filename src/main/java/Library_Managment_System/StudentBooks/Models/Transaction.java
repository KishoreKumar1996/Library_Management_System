package Library_Managment_System.StudentBooks.Models;

import Library_Managment_System.StudentBooks.Enums.TransactionStatus;
import Library_Managment_System.StudentBooks.Enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private TransactionStatus transactionStatus;

    private int fine;

    //trans type -> issued/ returned
    @Column(name = "Transaction_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    //shareable ID, check payment gateway... randomly generated
    private String transactionId = UUID.randomUUID().toString();

    @CreationTimestamp
    private Date transactionDate;

    /*
    //mapping - unidirectional
    1. transaction: book :: Many: 1 :: Child : Parent
       1 book can be transitioned many times
    2. transaction: card :: Many: 1 :: Child : Parent
       1 card can make many transactions
    */
    @ManyToOne
    @JoinColumn
    private Card card;  //primary key of Book entity -> foreign key

    @ManyToOne
    @JoinColumn
    private Book book; //primary key of Card entity -> foreign key

    //constructor
    public Transaction() {

    }

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public TransactionStatus getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(TransactionStatus transactionStatus) { this.transactionStatus = transactionStatus; }

    public int getFine() { return fine; }
    public void setFine(int fine) { this.fine = fine; }

    public TransactionType getTransactionType() { return transactionType; }
    public void setTransactionType(TransactionType transactionType) { this.transactionType = transactionType; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }

    public Card getCard() { return card; }
    public void setCard(Card card) { this.card = card; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
}
