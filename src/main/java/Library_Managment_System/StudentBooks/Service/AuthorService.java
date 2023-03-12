package Library_Managment_System.StudentBooks.Service;


import Library_Managment_System.StudentBooks.DTOs.AuthorRequestDTO;
import Library_Managment_System.StudentBooks.DTOs.AuthorResponseDTO;
import Library_Managment_System.StudentBooks.DTOs.BookResponseDTO;
import Library_Managment_System.StudentBooks.Models.Author;
import Library_Managment_System.StudentBooks.Models.Book;
import Library_Managment_System.StudentBooks.Repo.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

//    public String addAuthor(Author author) {
//        authorRepository.save(author);
//        return "Author added successfully.";
//    }

    //... using author DTO ...//
    public String addAuthor(AuthorRequestDTO authorRequestDTO) {
        //in the params -> object is of type DTO
        //repo interacts only with entity objects...

        //so convert authorEntryDTO to author entity

        //1. create author object
        Author author = new Author();

        //2. set attributes, to set correct values in db
        //extract values from DTO object, and set values in entity obj
        author.setName(authorRequestDTO.getName());
        author.setAge(authorRequestDTO.getAge());
        author.setCountry(authorRequestDTO.getCountry());

        //save changes
        authorRepository.save(author);
        return "Author added successfully.";
    }

    //infinite recursion
//    public Author getAuthor(Integer authorId) {
//        return authorRepository.findById(authorId).get();
//    }

    public AuthorResponseDTO getAuthor(Integer authorId) {
        //extract required values from author and put in authorResponseDTO
        Author author = authorRepository.findById(authorId).get();

        //create response DTO
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();

        //set attributes for bookResponseDTO
        //1. get list of books written
        List<Book> booksWritten = author.getBooksWritten();

        //2. convert book list into book response DTO
        List<BookResponseDTO> booksWrittenDTO = new ArrayList<>();
        for(Book b: booksWritten) {
            //create new object
            BookResponseDTO bookResponseDTO = new BookResponseDTO();

            //set attributes
            bookResponseDTO.setGenre(b.getGenre());
            bookResponseDTO.setName(b.getName());
            bookResponseDTO.setPages(b.getPages());
            bookResponseDTO.setRating(b.getRating());

            //add bookResponseDTO to booksWritten (list)\
            booksWrittenDTO.add(bookResponseDTO);
        }

        //set attributes for authorResponseDTO
        authorResponseDTO.setAge(author.getAge());
        authorResponseDTO.setCountry(author.getCountry());
        authorResponseDTO.setName(author.getName());
        authorResponseDTO.setBooksWritten(booksWrittenDTO);

        return authorResponseDTO;
    }
}
