package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Role;
import my.examples.pouch.repository.AccountRepository;
import my.examples.pouch.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    public List<Account> findAccountAll(){
        return accountRepository.findAll();
    }

    @Transactional
    public Account join(Account account) {
        Role role = roleRepository.getRoleByName("user");
        account.addRole(role);
        return accountRepository.save(account);
    }

    @Transactional
    public void deleteAccount(Long id){
        accountRepository.deleteById(id);
    }

    public Account findAccountByEmail(String email) {
        return accountRepository.findMyAccount(email);
    }
}
