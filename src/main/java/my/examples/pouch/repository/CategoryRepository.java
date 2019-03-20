package my.examples.pouch.repository;

import my.examples.pouch.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountCategoryRepository extends JpaRepository<Category,Long>{
    @Query("SELECT c FROM Category c WHERE c.id=:id")
    Category findMyCategory(@Param("id") Long id);
}
