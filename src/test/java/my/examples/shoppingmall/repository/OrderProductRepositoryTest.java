package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.OrderProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class OrderProductRepositoryTest {
    @Autowired
    OrderProductRepository orderProductRepository;

    @Test
    public void init(){

    }

//    @Test
//    public void findByOrderList(){
//        Pageable pageable = PageRequest.of(0,2);
//        Page<OrderProduct> all = orderProductRepository.findAllMyOrderList(2L,pageable);
//
//        for(OrderProduct list : all) {
//            System.out.println(list.getId()+","+list.getAmount()+","+list.getTotalPrice());
//        }
//    }
}
