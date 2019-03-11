package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="account_repository")
@Getter
@Setter
public class AccountAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String phone1;
    private String phone2;
    @Column(name="zip_code")
    private int zipCode;
    private String addr1;
    private String addr2;
    private String message;
    @Column(name="reg_date")
    private LocalDateTime regDate;
}
