package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.dto.custom.CustomCategory;
import my.examples.pouch.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    public Category getCategory(Long id);
    public List<Category> findMyCategoryList(String email);
    public List<Category> getCategoriesBySearch(int searchType, String searchStr);
    public List<CustomCategory> getCustomCategories(List<Category> categories);
    public Category addCategory(Category category);
    public Category saveCategory(Category sharedCategory);
    public void deleteCategory(Long id);
    public CustomCategory getCustomCategory(Category category);
    public Category shareCategory(String email, Category category);
}
