package Library_Managment_System.StudentBooks.Contoller;

import Library_Managment_System.StudentBooks.DTOs.AuthorRequestDTO;
import Library_Managment_System.StudentBooks.DTOs.AuthorResponseDTO;
import Library_Managment_System.StudentBooks.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

//... using author entity ...//
//    @PostMapping("/add")
//    public String addAuthor(@RequestBody Author author) {
//        return authorService.addAuthor(author);
//    }

    //... using author DTO ...//
    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return authorService.addAuthor(authorRequestDTO);
    }

    //will result in infinite recursion
    //as author has book obj, book has author obj... keep calling each other -> stack overflow error
//    @GetMapping("getAuthor")
//    public Author getAuthor(@RequestParam("authorId") Integer authorId) {
//        return authorService.getAuthor(authorId);
//    }

    @GetMapping("getAuthor")
    public AuthorResponseDTO getAuthor(@RequestParam("authorId") Integer authorId) {
        return authorService.getAuthor(authorId);
    }
}
