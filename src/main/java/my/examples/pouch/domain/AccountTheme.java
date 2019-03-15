package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.jdo.annotations.Join;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="account_theme")
@Getter
@Setter
public class AccountTheme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="color_option")
    private int colorOption = 0;
    @Column(name="link_option")
    private int linkOption = 0;
    @Column(name="list_option")
    private int listOption = 0;
    @Column(name="background_option")
    private Long backgroundOption;
    @Column(name="reg_date")
    private Date regDate;

    @OneToMany
    @JoinColumn(name="theme_id")
    private List<BackgroundImage> backgroundImageList;
}
