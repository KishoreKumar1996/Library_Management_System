package Library_Managment_System.StudentBooks.Repo;

import Library_Managment_System.StudentBooks.Models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
