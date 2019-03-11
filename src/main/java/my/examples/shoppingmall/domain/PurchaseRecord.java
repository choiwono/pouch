package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="purchase_record")
@Getter
@Setter
public class PurchaseRecord {
    @Id
    private long id;
    private int price;
    @Column(name="purchase_date")
    private Date purchaseDate;
}
