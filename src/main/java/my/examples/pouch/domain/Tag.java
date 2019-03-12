package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tag")
@Getter
@Setter
public class Tag{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="account_id")
    private String accountId;
    private String tagName;
    @Column(name="reg_date")
    private Date regDate;

    @ManyToMany
    @JoinTable(name="tag_mapping",
            joinColumns = {@JoinColumn(name = "tag_id",referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "board_id",referencedColumnName = "id")}
    )
    private Set<Board> boards;
}
