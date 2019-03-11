package my.examples.shoppingmall.repository;

import my.examples.shoppingmall.domain.Account;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Test
    public void init() {

    }

    @Test
    public void findAll() throws Exception {
        List<Account> all = accountRepository.findAll();
        for(Account list : all) {
            System.out.println(list.getName());
        }
    }

    @Test
    public void findMyGrades() throws Exception {
        Optional<Account> account = accountRepository.findById(1L);
        System.out.println(account.get().getEmail());
    }

    @Test
    public void findMyOrderList() throws Exception {

    }
}
