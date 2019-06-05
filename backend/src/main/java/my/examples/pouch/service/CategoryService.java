package my.examples.pouch.service;

import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.custom.CustomCategory;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category getCategory(Long id);
    List<Category> findMyCategoryList(String email);
    List<Category> getCategoriesBySearch(int searchType, String searchStr);
    List<CustomCategory> getCustomCategories(List<Category> categories);
    Category addCategory(Category category);
    Category save(Category sharedCategory);
    void deleteCategory(Long id);
    CustomCategory getCustomCategory(Category category);
    Category saveCategory(String email, Category category);
    int updateCategory(Long id, String title);
}
