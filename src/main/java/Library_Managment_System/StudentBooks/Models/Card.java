package Library_Managment_System.StudentBooks.Models;
import Library_Managment_System.StudentBooks.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {
/*
plan -> save card in database
before saving, set all of it's attributes...
att #1 -> id, auto generated, need not be set
att #2 -> createdOn, auto generated, need not be set
att #3 -> updatedOn, auto generated, need not be set
att #4 -> cardStatus, set in student service
att #5 -> student, foreign key
*/

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp //auto timestamps the time when an entry is created
    private Date createdOn;    //util.Date -> has a detailed Time Stamp : DD:MM:YYYY:HH:MM:SS:MS

    @UpdateTimestamp   //sets time to when entry was last updated
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING) //sets foreign key
    private CardStatus cardStatus;

    //mapping
    //unidirectional mapping -> card: student :: Child: Parent :: One: One
    @OneToOne
    @JoinColumn
    //make an object of parent entity, to which connection is to be made with
    //student -> variable name of Parent class entity, foreign key attribute
    private Student student; //used in parent class, for bidirectional mapping

    //bidirectional mapping -> card: book :: Parent: Child :: One: Many
    //many books can be issued from one card
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Book> booksIssued;

    //bidirectional mapping -> card: transaction :: Parent: Child :: 1: many
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactionList = new ArrayList<>();

    //constructors
    public Card() {
        //initialise the books issued list
        booksIssued = new ArrayList<>();
    }

    //getters and setters
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getCreatedOn() { return createdOn; }
    public void setCreatedOn(Date createdOn) { this.createdOn = createdOn; }

    public Date getUpdatedOn() { return updatedOn; }
    public void setUpdatedOn(Date updatedOn) { this.updatedOn = updatedOn; }

    public CardStatus getCardStatus() { return cardStatus; }
    public void setCardStatus(CardStatus cardStatus) { this.cardStatus = cardStatus; }

    public List<Book> getBooksIssued() { return booksIssued; }
    public void setBooksIssued(List<Book> booksIssued) { this.booksIssued = booksIssued; }

    public List<Transaction> getTransactionList() { return transactionList; }
    public void setTransactionList(List<Transaction> transactionList) { this.transactionList = transactionList; }
}