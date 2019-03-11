package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void init() {

    }

    @Test
    public void findAllList(){
        List<Product> all = productRepository.findAll();
        for(Product list :all) {
            System.out.println(list.getName());
        }
    }

    @Test
    public void findByCategory(){
        Pageable pageable = PageRequest.of(0,5);
        Page<Product> all = productRepository.findProductByCategory(2L,pageable);
        long totalElements = all.getTotalElements();
        int totalPage = all.getTotalPages();
        for(Product list:all) {
            System.out.println(list.getName());
        }
    }
}
