package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="board")
@Getter
@Setter
public class Board {
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

    @Column(name="board_option")
    private Long boardOption;

    @ManyToMany(mappedBy = "boards")
    private Set<Tag> tags;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_category_id")
    private AccountCategory accountCategory;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;

    public Board(){
        tags = new HashSet<>();
        regDate = new Date();
    }
}
