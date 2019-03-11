package my.examples.shoppingmall.repository;


import my.examples.shoppingmall.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    @Query("SELECT p FROM ProductCategory p ORDER BY p.ordering ASC")
    List<ProductCategory> getFindAll();
}
