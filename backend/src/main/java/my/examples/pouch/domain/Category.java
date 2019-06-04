package my.examples.pouch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "category",
            fetch=FetchType.LAZY)
    private List<Link> links;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Category(){
        regDate = new Date();
    }
}
