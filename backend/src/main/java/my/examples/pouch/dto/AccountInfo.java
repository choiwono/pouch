package my.examples.pouch.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class AccountInfo {
    private Long id;
    private String email;
    private Collection<GrantedAuthority> roles;
}
