package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Transactional
    public Category getAccountCategory(Long id) {
        return categoryRepository.findMyCategory(id);
    }

    public List<Category> findMyCategoryList(String email) {
        return categoryRepository.findMyCategoryByEmail(email);
    }
}
