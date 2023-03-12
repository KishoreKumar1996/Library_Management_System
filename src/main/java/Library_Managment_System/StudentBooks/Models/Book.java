package Library_Managment_System.StudentBooks.Models;

import Library_Managment_System.StudentBooks.Enums.BookGenre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/*
book: author :: many: 1
author -> parent class, book -> child class
*/
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int pages;
    private double rating;
    private boolean issued;
    @Enumerated(value = EnumType.STRING)
    private BookGenre genre;

    //mapping
    //unidirectional mapping -> book: author :: Child: Parent :: Many: One
    //set foreign key -> 3 steps -
    @ManyToOne
    @JoinColumn
    private Author author;

    //unidirectional mapping -> book: card :: Child: Parent :: Many: One
    @ManyToOne
    @JoinColumn
    private Card card;

    //bidirectional mapping -> book: transaction:: Parent: Child :: 1: Many
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Transaction> listOfTransactions = new ArrayList<>();

    //constructor
    public Book() {

    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public BookGenre getGenre() { return genre; }
    public void setGenre(BookGenre genre) { this.genre = genre; }

    public boolean isIssued() { return issued; }
    public void setIssued(boolean issued) { this.issued = issued; }

    public List<Transaction> getListOfTransactions() { return listOfTransactions; }
    public void setListOfTransactions(List<Transaction> listOfTransactions) { this.listOfTransactions = listOfTransactions; }
}