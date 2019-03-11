package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="friends")
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date regDate;

    @OneToMany
    @JoinTable(name="friends")
    private List<Message> messages;

    public Friends(){
        regDate = new Date();
    }
}
