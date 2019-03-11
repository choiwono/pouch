package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="product_category")
@Getter
@Setter
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=50,name="category_name")
    private String categoryName;
    @Column(name="reg_date")
    private Date regDate;
    private int ordering;

    @OneToMany(mappedBy = "productCategory")
    private Set<Product> productList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private ProductCategory groupCategory;

    @OneToMany(mappedBy = "groupCategory")
    private Set<ProductCategory> groupCategories;

    public ProductCategory() {
        regDate = new Date();
        groupCategories = new HashSet<>();
        productList = new HashSet<>();
    }
}
