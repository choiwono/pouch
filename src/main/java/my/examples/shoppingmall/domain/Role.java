package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length=50)
    private String name;
    @Column(name="reg_date")
    private Date regDate;

    public Role() {
        regDate = new Date();
    }
}
