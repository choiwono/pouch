package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order_product")
@Setter
@Getter
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    private int totalPrice;
    @Column(length=50)
    private String name;
    @Column(name="reg_date")
    private Date regDate;

    @ManyToOne
    @JoinColumn(name = "proudct_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @ManyToOne
//    @JoinColumn(name = "account_id")
//    private Account account;

    public OrderProduct() {
        regDate = new Date();
        amount = 0;
        totalPrice = 0;
    }
}
