package my.examples.shoppingmall.dto;

import lombok.Data;

import java.util.List;

@Data
public class CartItem {
    private Long productId;
    private int quantity;
}
