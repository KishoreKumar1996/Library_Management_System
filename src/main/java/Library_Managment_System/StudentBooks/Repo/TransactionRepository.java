package Library_Managment_System.StudentBooks.Repo;

import Library_Managment_System.StudentBooks.Enums.TransactionStatus;
import Library_Managment_System.StudentBooks.Enums.TransactionType;
import Library_Managment_System.StudentBooks.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    //get required transaction from JPA using custom query
    //JPA Query lang ->
    @Query("select t from Transaction t where t.card.id=:cardId and t.book.id=:bookId and t.transactionType=:type and t.transactionStatus=:status")
    public List<Transaction> findTransaction(int cardId, int bookId, TransactionType type, TransactionStatus status);

}

