package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.jdo.annotations.Join;
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

    @OneToOne(mappedBy = "account")
    private AccountTheme accountTheme;

    @OneToMany
    @JoinColumn(name="account_id")
    private List<Board> boards;

    @OneToMany
    @JoinColumn(name="account_id")
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
