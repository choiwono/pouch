package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long>{

}
