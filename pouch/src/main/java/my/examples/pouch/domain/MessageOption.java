package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="message_option")
@Getter
@Setter
public class MessageOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="opt_type")
    private int optType;
    @Column(name="reg_date")
    private Date regDate;
    @Column(name="type_id")
    private Long typeId;

    @OneToOne(mappedBy = "messageOption")
    private Message message;

    public MessageOption(){
        regDate = new Date();
    }
}
