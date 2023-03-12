package Library_Managment_System.StudentBooks.Contoller;

import Library_Managment_System.StudentBooks.DTOs.BookRequestDTO;
import Library_Managment_System.StudentBooks.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

//    @PostMapping("add")
//    public String addBook(@RequestBody Book book) {
//        return bookService.addBook(book);
//    }

    @PostMapping("add")
    public String addBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookService.addBook(bookRequestDTO);
    }

}
