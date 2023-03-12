package Library_Managment_System.StudentBooks.DTOs;

import java.util.List;

public class AuthorResponseDTO {
    private String name;
    private int age;
    private String country;
    private List<BookResponseDTO> booksWritten;

    //constructor
    public AuthorResponseDTO() {
    }

    //getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public List<BookResponseDTO> getBooksWritten() { return booksWritten; }
    public void setBooksWritten(List<BookResponseDTO> booksWritten) { this.booksWritten = booksWritten; }
}
