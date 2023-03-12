package Library_Managment_System.StudentBooks.DTOs;

import Library_Managment_System.StudentBooks.Enums.BookGenre;

public class BookResponseDTO {
    private String name;
    private int pages;
    private double rating;
    private BookGenre genre;

    //we use this in author entity, so no need to have author related attributes

    //constructor
    public BookResponseDTO() {
    }

    //getters and setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public BookGenre getGenre() { return genre; }
    public void setGenre(BookGenre genre) { this.genre = genre; }
}
