package my.examples.pouch.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name="category_id")
    private Long categoryId;

    @Column(name="use_yn")
    private String useYn;

    @Column(name="reg_date")
    private Date regDate;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="options_id",referencedColumnName = "id")
    private MessageOption messageOption;

    public Message(){
        regDate = new Date();
    }
}
