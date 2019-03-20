package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.AccountCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountCategoryService {
    private final AccountCategoryRepository accountCategoryRepository;

    @Transactional
    public Category getAccountCategory(Long id) {
        return accountCategoryRepository.findMyCategory(id);
    }
}
