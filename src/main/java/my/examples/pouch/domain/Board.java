package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

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
    private int favorites;
    private int repository;

    @OneToMany
    @JoinColumn(name="board_id")
    private List<Message> messages;
}
