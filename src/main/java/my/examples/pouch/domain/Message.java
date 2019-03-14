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
    @Column(name="receive_id")
    private String receiveId;
    @Column(name="sender_id")
    private String senderId;
    @Column(nullable = false)
    private int option;
    @Column(name="option_name")
    private String optionName;
    @Column(name="reg_date")
    private Date regDate;

    @OneToOne(mappedBy = "message")
    private MessageRecord messageRecord;
}
