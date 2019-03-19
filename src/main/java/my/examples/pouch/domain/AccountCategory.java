package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="account_category")
@Getter
@Setter
public class AccountCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="reg_date")
    private Date regDate;
    @Column(name="ordering")
    private int ordering;

    @OneToMany(mappedBy = "accountCategory")
    private List<Board> boards;

    public AccountCategory(){
        regDate = new Date();
    }
}
