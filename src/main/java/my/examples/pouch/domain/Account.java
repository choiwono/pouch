package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length=20)
    private String name;
    @Column(length=20, name="nick_name")
    private String nickName;
    private String email;
    private String passwd;

    @Column(name="reg_date")
    private Date regDate;

    @ManyToMany
    @JoinTable(name="account_roles",
            joinColumns = {@JoinColumn(name = "account_id",referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @ManyToMany
    @JoinTable(name="account_friends",
            joinColumns = {@JoinColumn(name = "account_id",referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "friends_id",referencedColumnName = "id")}
    )
    private List<Account> friends;

    @OneToMany
    @JoinTable(name="account")
    private List<Board> boards;

    @OneToMany
    @JoinTable(name="account")
    private List<Message> messages;

    public Account() {
        regDate = new Date();
        roles = new HashSet<>();
    }

    public void addRole(Role role){
        if(roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
