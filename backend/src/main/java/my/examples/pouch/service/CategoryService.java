package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Category getAccountCategory(Long id) {
        return categoryRepository.findMyCategory(id);
    }

    public List<Category> findMyCategoryList(String email) {
        return categoryRepository.findMyCategoryByEmail(email);
    }

    final static int SEARCH_BY_CATEGORY = 1;
    final static int SEARCH_BY_TAG = 2;

    public List<Category> getCategoriesBySearch(int searchType, String searchStr) {

        if (searchType == SEARCH_BY_CATEGORY) {
            return categoryRepository.searchCategory(searchStr);
        } else{
            return categoryRepository.searchTag(searchStr);
        }
    }
}
