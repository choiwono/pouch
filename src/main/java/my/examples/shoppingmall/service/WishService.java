package my.examples.shoppingmall.service;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Account;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.domain.Wish;
import my.examples.shoppingmall.repository.RoleRepository;
import my.examples.shoppingmall.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishRepository wishRepository;

    @Transactional(readOnly = true)
    public Wish findByAccountAndProduct(Account account, Product product) {
        return wishRepository.findByAccountAndProduct(account.getId(),product.getId());
    }

    @Transactional
    public void saveWishItem(Account account, Product product) {
        Wish wish = new Wish();
        wish.setAccount(account);
        wish.setProduct(product);
        wishRepository.save(wish);
    }

    @Transactional(readOnly=true)
    public List<Wish> findMyWishList(Long id) {
        return wishRepository.findByAccountId(id);
    }

    @Transactional
    public void deleteByAccountAndProduct(Long accountId, Long productId) {
        wishRepository.deleteByAccountAndProduct(accountId,productId);
    }
}
