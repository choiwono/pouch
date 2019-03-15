package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import my.examples.pouch.dto.UseEnum;

import javax.jdo.annotations.Join;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    @Column(name="use_yn")
    private UseEnum useYn;
    @Column(name="reg_date")
    private Date regDate;

    public Message(){
        regDate = new Date();
    }
}
