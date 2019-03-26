package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="link")
@Getter
@Setter
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Lob
    private String url;
    private String email;

    @Column(name="reg_date")
    private Date regDate;
    private Long repository;

    @Column(name="link_option")
    private Long linkOption;

    @ManyToMany(mappedBy = "links")
    private Set<Tag> tags;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Link(){
        tags = new HashSet<>();
        regDate = new Date();
    }
}
