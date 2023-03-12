package Library_Managment_System.StudentBooks.Contoller;

import Library_Managment_System.StudentBooks.DTOs.StudentMobileUpdateDTO;
import Library_Managment_System.StudentBooks.Models.Student;
import Library_Managment_System.StudentBooks.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("/get_student")
    public String getNameByEmail(@RequestParam("email") String email) {
        return studentService.findNameByEmail(email);
    }

//    @PutMapping("/update_mobile")
//    public String updateMobile(@RequestBody Student student) {
//        return studentService.updateMobile(student);
//    }

    //use DTO -> lighter than using entire entity with multiple attribute, faster API
    @PutMapping("update_mobile")
    public String updateMobile(@RequestBody StudentMobileUpdateDTO studentMobileUpdateDTO) {
        return studentService.updateMobile(studentMobileUpdateDTO);
    }

}
