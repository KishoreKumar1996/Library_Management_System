package Library_Managment_System.StudentBooks.Contoller;

import Library_Managment_System.StudentBooks.DTOs.IssuedBookRequestDTO;
import Library_Managment_System.StudentBooks.DTOs.ReturnBookRequestDTO;
import Library_Managment_System.StudentBooks.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("issueBook")
    public String issueBook(@RequestBody IssuedBookRequestDTO issueBookRequestDTO) {
        try {
            return transactionService.issueBook(issueBookRequestDTO);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("returnBook")
    public String returnBook(@RequestBody ReturnBookRequestDTO returnBookRequestDTO) {
        try {
            return transactionService.returnBook(returnBookRequestDTO);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
}
