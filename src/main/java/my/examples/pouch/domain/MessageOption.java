package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import my.examples.pouch.dto.UseEnum;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="message_option")
@Getter
@Setter
public class MessageOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="opt_name")
    private String optName;
    @Enumerated(EnumType.STRING)
    @Column(name="use_yn")
    private UseEnum useYn;
    @Column(name="reg_date")
    private Date regDate;

    @OneToMany
    @JoinColumn(name="options_id")
    private List<Message> messages;

    public MessageOption(){
        regDate = new Date();
    }
}
