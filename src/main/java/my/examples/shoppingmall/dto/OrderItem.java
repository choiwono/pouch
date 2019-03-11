package my.examples.shoppingmall.dto;

import lombok.Data;

@Data
public class OrderItem {
    private Long productId;
    private int quantity;
    private int price;
    private int totalPrice;
}
