package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.Order;
import my.examples.shoppingmall.dto.RecordItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE o.userName=:userName and " +
            "concat(o.email1,'@',o.email2)=:email and o.phone=:phone ORDER BY o.regDate DESC")
    List<Order> findRecordByGuest(@Param("userName") String userName,
                                  @Param("email") String email, @Param("phone") String phone);

    @Query("SELECT o FROM Order o" +
           //" inner join fetch o.orderProductList.product" +
           " WHERE concat(o.email1,'@',o.email2)=:email")
    List<Order> findMyOrderList(@Param("email") String email);
}
