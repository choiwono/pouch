package my.examples.shoppingmall.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Account;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.domain.Wish;
import my.examples.shoppingmall.dto.CartItem;
import my.examples.shoppingmall.dto.OrderItem;
import my.examples.shoppingmall.service.AccountService;
import my.examples.shoppingmall.service.ProductService;
import my.examples.shoppingmall.service.WishService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;
    private final WishService wishService;
    private final ProductService productService;

    @PostMapping(value="/wishItem/{id}")
    public String addWishItem(@PathVariable(value="id") Long id, Principal principal){
        String result = "fail";
        if(principal != null){
            Account account = accountService.findAccountByEmail(principal.getName());
            Product product = productService.findByIdProduct(id);
            Wish wish = wishService.findByAccountAndProduct(account,product);
            if(wish != null){
                result = "duplicate";
            } else {
                wishService.saveWishItem(account,product);
                result = "success";
            }
        }
        return result;
    }

    @DeleteMapping(value="/wishItem/{id}")
    public String deleteWishItem(@PathVariable(value="id") Long id, Principal principal){
        String result = "fail";
        if(principal != null){
            Account account = accountService.findAccountByEmail(principal.getName());
            wishService.deleteByAccountAndProduct(account.getId(),id);
            result = "success";
        }
        return result;
    }

    @PostMapping(value="/checkEmail/{id}")
    public String checkEmail(@PathVariable(value="id") String email){
        String result = "fail";
        Account account = accountService.findAccountByEmail(email);
        if(account == null){
            result = "success";
        } else {
            result = "duplicate";
        }
        return result;
    }
}