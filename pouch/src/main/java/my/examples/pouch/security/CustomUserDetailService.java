package my.examples.pouch.security;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Role;
import my.examples.pouch.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Override
    @Transactional(readOnly=false)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findMyAccount(email);
        if(account == null)
            throw new UsernameNotFoundException(email + "에 해당하는 사용자가 없습니다.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = account.getRoles();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }

        CustomSecurityUser customSecurityUser = new CustomSecurityUser(email,account.getPasswd(), authorities);
        customSecurityUser.setId(account.getId());
        customSecurityUser.setName(account.getName());
        customSecurityUser.setNickName(account.getNickName());
        return customSecurityUser;
    }
}
