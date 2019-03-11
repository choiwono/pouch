package my.examples.shoppingmall.controller;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Product;
import my.examples.shoppingmall.dto.CartItem;
import my.examples.shoppingmall.dto.ProductItem;
import my.examples.shoppingmall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;

    @GetMapping("/list")
    public String cartList(Model model,
                           HttpSession session){
        if(session.getAttribute("cart") == null) {
            model.addAttribute("products",null);
        } else {
            Map<Long, Integer> cart = (Map) session.getAttribute("cart");
            List<ProductItem> products = productService.findMyProductList(cart);
            int totalPrice = productService.findTotalPrice(products);
            if(totalPrice > 0){
                model.addAttribute("totalPrice", totalPrice);
            }
            model.addAttribute("products", products);
        }
        return "cart/list";
    }
}
