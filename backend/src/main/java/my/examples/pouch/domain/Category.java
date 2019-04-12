package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="category_name")
    private String categoryName;
    @Column(name="reg_date")
    private Date regDate;
    @Column(name="ordering")
    private int ordering;

    @OneToMany(mappedBy = "category",
            fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Link> links;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Category(){
        regDate = new Date();
    }
}
