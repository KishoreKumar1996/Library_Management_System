package Library_Managment_System.StudentBooks.Repo;

import Library_Managment_System.StudentBooks.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
     Student findByEmail(String email);

//     List<Students> findByAge(int age);

}
