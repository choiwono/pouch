package my.examples.shoppingmall.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.dto.OrderItem;
import my.examples.shoppingmall.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderApiController {
    private final ProductService productService;

    @PostMapping(value="/change")
    public OrderItem changeList(@RequestBody OrderItem orderItem,
                                Principal principal,
                                HttpSession session){

        orderItem.setQuantity(orderItem.getQuantity());
        orderItem.setPrice(orderItem.getPrice());
        orderItem.setTotalPrice(orderItem.getPrice() * orderItem.getQuantity());
        return orderItem;
    }
}
