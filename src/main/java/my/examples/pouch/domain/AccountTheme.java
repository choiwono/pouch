package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="account_theme")
@Getter
@Setter
public class AccountTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="color_option")
    private int colorOption = 0;
    @Column(name="link_option")
    private int linkOption = 0;
    @Column(name="list_option")
    private int listOption = 0;
    @Column(name="reg_date")
    private Date regDate;

    @OneToOne
    @JoinColumn(name="account_id", referencedColumnName = "id", nullable = false)
    private Account account;
}
