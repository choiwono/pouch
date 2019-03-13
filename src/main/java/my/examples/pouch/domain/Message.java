package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="message")
@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="reg_date")
    private Date regDate;

}
