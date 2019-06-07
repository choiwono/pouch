package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Role;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.repository.AccountRepository;
import my.examples.pouch.repository.RoleRepository;
import my.examples.pouch.service.AccountService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAccountAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account join(Joinform joinform) {
        Account account = new Account();
        account.setName(joinform.getName());
        account.setEmail(joinform.getEmail());
        account.setNickName(joinform.getNickname());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPasswd(passwordEncoder.encode(joinform.getPasswd()));
        Role role = roleRepository.getRoleByName("USER");
        account.addRole(role);
        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccountByEmail(String email) {
        return accountRepository.findMyAccount(email);
    }

    @Override
    @Transactional
    public String updateUserPassword(Account account) {
        String password = UUID.randomUUID().toString();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        account.setPasswd(passwordEncoder.encode(password));
        accountRepository.updatePasswordByEmail(account.getEmail(), account.getPasswd());
        return account.getPasswd();
    }
}
