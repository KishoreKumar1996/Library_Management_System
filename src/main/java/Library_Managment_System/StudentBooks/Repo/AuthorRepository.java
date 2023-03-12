package Library_Managment_System.StudentBooks.Repo;

import Library_Managment_System.StudentBooks.Models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
