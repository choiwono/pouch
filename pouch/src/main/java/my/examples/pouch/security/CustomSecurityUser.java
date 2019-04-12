package my.examples.pouch.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomSecurityUser extends User {
    private Long id;
    private String name;
    private String nickName;

    public CustomSecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, true, true,true,true, authorities);
    }

    public String getEmail(){
        return super.getUsername();
    }
}
