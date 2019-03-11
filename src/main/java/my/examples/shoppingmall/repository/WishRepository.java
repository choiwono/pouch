package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.Role;
import my.examples.shoppingmall.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WishRepository extends JpaRepository<Wish,Long> {
    @Query("SELECT w FROM Wish w WHERE w.account.id=:accountId AND w.product.id=:productId")
    public Wish findByAccountAndProduct(@Param("accountId") Long accountId, @Param("productId") Long productId);

    @Query("SELECT w FROM Wish w WHERE w.account.id=:accountId")
    List<Wish> findByAccountId(@Param("accountId") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Wish w WHERE w.account.id=:accountId AND w.product.id=:productId")
    public void deleteByAccountAndProduct(@Param("accountId") Long accountId, @Param("productId") Long productId);
}
