package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="review")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=50)
    private String title;
    @Lob
    private String content;
    private Double rating;
    @Column(name="reg_date")
    private Date regDate;

//    @ManyToOne
//    @JoinColumn(name="account_id")
//    private Account account;

//    @ManyToOne
//    @JoinColumn(name="product_id")
//    private Product product;

    public Review(){
        rating = 0.0;
        regDate = new Date();
    }
}
