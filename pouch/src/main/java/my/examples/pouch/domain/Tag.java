package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@Setter
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name="tag_name")
    private String tagName;
    private int options;
    @Column(name = "reg_date")
    private Date regDate;

    @ManyToMany
    @JoinTable(name = "tag_mapping",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "board_id", referencedColumnName = "id")}
    )
    private Set<Link> links;

    public Tag() {
        links = new HashSet<>();
    }
}


