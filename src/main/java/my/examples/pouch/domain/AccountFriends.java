package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name="account_friends")
@Getter
@Setter
public class AccountFriends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="reg_date")
    private Date regDate;

    @ManyToOne
    @JoinColumn(name="friends_id")
    private Account account;

    public AccountFriends(){
        regDate = new Date();
    }
}
