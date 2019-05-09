package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Role;
import my.examples.pouch.repository.AccountRepository;
import my.examples.pouch.repository.RoleRepository;
import my.examples.pouch.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Account join(Account account) {
        Role role = roleRepository.getRoleByName("user");
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
    public void updateUserPassword(Account account) {
        accountRepository.updatePasswordByEmail(account.getEmail(), account.getPasswd());
    }
}
