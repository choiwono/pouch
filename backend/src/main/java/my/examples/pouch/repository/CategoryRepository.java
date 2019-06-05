package my.examples.pouch.repository;

import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>, CategoryRepositoryCustom {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.links WHERE c.id=:id")
    Category getCategories(@Param("id") Long id);

    @Query("SELECT c FROM Category c inner join fetch c.account" +
           " WHERE c.account.email=:email")
    List<Category> findMyCategoryByEmail(String email);

    @Query("SELECT DISTINCT c FROM Category c INNER JOIN FETCH c.links l"+
            " INNER JOIN FETCH l.tags t WHERE t.tagName like concat('%',:searchStr,'%')")
    List<Category> searchTag(String searchStr);

    @Query("SELECT DISTINCT c FROM Category c WHERE c.categoryName like concat('%',:searchStr,'%')")
    List<Category> searchCategory(String searchStr);

    @Query("SELECT COUNT(DISTINCT c) FROM Category c INNER JOIN c.links l"+
            " INNER JOIN l.tags t WHERE t.tagName like concat('%',:searchStr,'%')")
    Long countSearchTag(String searchStr);

    @Query(value = "INSERT INTO Category (account_id, category_name, ordering) ", nativeQuery = true)
    Category share(Category category);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DELETE FROM category AS c WHERE c.id=:id", nativeQuery = true)
    void deleteByCategoryId(@Param("id") Long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE Category c SET c.categoryName=:title WHERE c.id=:id")
    int updateCategory(@Param("id") Long id, @Param("title") String title);
}
