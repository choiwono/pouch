package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Role;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.repository.AccountRepository;
import my.examples.pouch.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface AccountService {
    public List<Account> findAccountAll();
    public Account join(Joinform joinform);
    public void deleteAccount(Long id);
    public Account findAccountByEmail(String email);
    public String updateUserPassword(Account account);
}
