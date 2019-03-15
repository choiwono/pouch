package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
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
    private int repository;

    @Column(name="board_option")
    private int boardOption;

    @ManyToMany(mappedBy = "boards")
    private Set<Tag> tags;

    public Board(){
        tags = new HashSet<>();
    }

}
