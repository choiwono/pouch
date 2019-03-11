package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.FileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileImageRepository extends JpaRepository<FileImage,Long> {
    @Query("SELECT f FROM FileImage f WHERE f.product=:id")
    List<FileImage> findByProductId(Long id);
}
