package my.examples.pouch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private String src;
    private String content;
    @Column(name="reg_date")
    private Date regDate;
    private Long repository;
    @Column(name="tags_name")
    private String tagsName;

    @Column(name="link_option")
    private Long linkOption;

    @JsonIgnore
    @ManyToMany
    private Set<Tag> tags;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Link(){
        tags = new HashSet<>();
        regDate = new Date();
    }
}
