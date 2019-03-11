package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="user_auth")
    private int userAuth;
    @Column(name="user_name")
    private String userName;
    private Integer payment; // 결제수단
    private String email1;
    private String email2;
    private String phone;
    @Column(name="receiver_name")
    private String receiverName;
    @Column(name="receiver_phone")
    private String receiverPhone;
    @Column(name="zip_code")
    private int zipCode;
    private String addr1;
    private String addr2;
    private String message;
    @Column(name="total_price")
    private int totalPrice;
    @Column(name="order_No")
    private String orderNo;
    @Column(name="reg_date")
    private Date regDate;

    @OneToMany(mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<OrderProduct> orderProductList;

    public Order() {
        regDate = new Date();
        orderProductList = new ArrayList<>();
        totalPrice = 0;
    }
}
