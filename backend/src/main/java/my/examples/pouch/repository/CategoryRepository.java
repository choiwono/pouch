package my.examples.pouch.repository;

import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long>, CategoryRepositoryCustom {

    @Query("SELECT c FROM Category c WHERE c.id=:id")
    Category findMyCategory(@Param("id") Long id);

    @Query("SELECT c FROM Category c inner join fetch c.account" +
           " WHERE c.account.email=:email")
    List<Category> findMyCategoryByEmail(String email);

    @Query("SELECT DISTINCT c FROM Category c INNER JOIN FETCH c.links l"+
            " INNER JOIN FETCH l.tags t WHERE t.tagName like concat('%',:searchStr,'%')")
    List<Category> searchTag(String searchStr);

    @Query("SELECT COUNT(DISTINCT c) FROM Category c INNER JOIN c.links l"+
            " INNER JOIN l.tags t WHERE t.tagName like concat('%',:searchStr,'%')")
    Long countSearchTag(String searchStr);
}
