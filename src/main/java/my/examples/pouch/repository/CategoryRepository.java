package my.examples.pouch.repository;

import my.examples.pouch.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long>{

    @Query("SELECT c FROM Category c WHERE c.id=:id")
    Category findMyCategory(@Param("id") Long id);

    @Query("SELECT c FROM Category c inner join fetch c.account" +
           " WHERE c.account.email=:email")
    List<Category> findMyCategoryByEmail(String email);
}
