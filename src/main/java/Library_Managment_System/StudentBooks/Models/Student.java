package Library_Managment_System.StudentBooks.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student {
/*
plan -> save card in database
before saving, set all of it's attributes...
att #1 -> id, auto generated, need not be set
att #2 -> name, age, mobile, email, country -> basic attributes, set in postman by user
att #3 -> Card entity attribute, set in student service
*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String mobile;
    @Column(unique = true)
    private String email;
    private String country;

    //bidirectional mapping
    //name of variable of parent entity, that was written in child class, foreign key attribute
    //cascade -> ensures that if parent class data is deleted, child class data associated to it gets deleted as well
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    //in case of OneToOne mapping -> one child class entity is associated, so ->
    private Card card;

    //constructors
    public Student() {

    }

    public Student(int id, String name, int age, String mobile, String email, String country) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.country = country;
    }

    //getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Card getCard() { return card; }
    public void setCard(Card card) { this.card = card; }
}