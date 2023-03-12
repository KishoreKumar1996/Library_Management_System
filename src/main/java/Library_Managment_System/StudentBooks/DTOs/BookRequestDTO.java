package Library_Managment_System.StudentBooks.DTOs;

import Library_Managment_System.StudentBooks.Enums.BookGenre;

public class BookRequestDTO {
        private String name;
        private int pages;
        private BookGenre genre;
        private int authorId;
        private double rating;

        public BookRequestDTO() {

        }

        public BookRequestDTO(String name, int pages, BookGenre genre, int authorId, double rating) {
            this.name = name;
            this.pages = pages;
            this.genre = genre;
            this.authorId = authorId;
            this.rating = rating;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getPages() { return pages; }
        public void setPages(int pages) { this.pages = pages; }

        public BookGenre getGenre() { return genre; }
        public void setGenre(BookGenre genre) { this.genre = genre; }

        public int getAuthorId() { return authorId; }
        public void setAuthorId(int authorId) { this.authorId = authorId; }

        public double getRating() { return rating; }
        public void setRating(double rating) { this.rating = rating; }
}
