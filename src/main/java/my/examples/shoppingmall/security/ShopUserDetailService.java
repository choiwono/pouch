package my.examples.shoppingmall.security;

import lombok.RequiredArgsConstructor;
import my.examples.shoppingmall.domain.Account;
import my.examples.shoppingmall.domain.Role;
import my.examples.shoppingmall.repository.AccountRepository;
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
public class ShopUserDetailService implements UserDetailsService {
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

        ShopSecurityUser shopSecurityUser = new ShopSecurityUser(email,account.getPasswd(), authorities);
        shopSecurityUser.setId(account.getId());
        shopSecurityUser.setName(account.getName());
        shopSecurityUser.setNickName(account.getNickName());
        return shopSecurityUser;
    }
}
