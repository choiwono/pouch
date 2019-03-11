package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="wish")
@Getter
@Setter
public class Wish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date regDate;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "proudct_id")
    private Product product;

    public Wish() {
        regDate = new Date();
    }
}
