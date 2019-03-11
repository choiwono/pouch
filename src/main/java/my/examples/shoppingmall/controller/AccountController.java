package my.examples.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.*;
import my.examples.shoppingmall.dto.Joinform;
import my.examples.shoppingmall.service.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final WishService wishService;
    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("/login")
    public String login(
            @RequestParam(name="fail",
                    required=false,defaultValue="false") String errorFlag,
            Model model){
        model.addAttribute("errorFlag",errorFlag);
        return "users/login";
    }

    @GetMapping("/join")
    public String joinform(){
        return "users/join";
    }

    @PostMapping("/join")
    public String joinform(@Valid Joinform joinform,
                           BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "users/joinfalse";
        }

        if(!joinform.getPasswd().equals(joinform.getPasswd2())){
            model.addAttribute("notEqualPassword","비밀번호가 서로 일치하지않습니다.");
            return "users/joinfalse";
        }

        Account emailCheck = accountService.findAccountByEmail(joinform.getEmail());
        if(emailCheck != null){
            model.addAttribute("duplicateEmail","중복된 이메일이 존재합니다.");
            return "users/joinfalse";
        }

        Account account = new Account();
        account.setEmail(joinform.getEmail());
        account.setName(joinform.getName());
        account.setNickName(joinform.getNickname());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        account.setPasswd(passwordEncoder.encode(joinform.getPasswd()));
        Account result = accountService.join(account);
        return "/users/welcome";
    }

    @GetMapping("/wishlist")
    public String wishList(Principal principal,
                           Model model){
        if(principal != null) {
            Account account = accountService.findAccountByEmail(principal.getName());
            List<Wish> wishList = wishService.findMyWishList(account.getId());
            List<Product> products = new ArrayList<>();
            for(Wish w : wishList){
                products.add(productService.findByIdProduct(w.getProduct().getId()));
            }
            model.addAttribute("products",products);
        }
        return "users/wishlist";
    }

    @GetMapping(value="/myorders")
    public String myOrders(Principal principal,
                           Model model){
        if(principal == null){
            model.addAttribute("message","회원만 접근하실수 있습니다");
        }

        List<Order> orders = orderService.findMyOrderList(principal.getName());
        model.addAttribute("orders",orders);
        System.out.println(principal.getName());
        return "users/myorders";
    }
}
