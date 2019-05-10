package my.examples.pouch.service;

import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.custom.CustomCategory;

import java.util.List;

public interface CategoryService {
    public Category getCategory(Long id);
    public List<Category> findMyCategoryList(String email);
    public List<Category> getCategoriesBySearch(int searchType, String searchStr);
    public List<CustomCategory> getCustomCategories(List<Category> categories);
    public Category addCategory(Category category);
    public Category save(Category sharedCategory);
    public void deleteCategory(Long id);
    public CustomCategory getCustomCategory(Category category);
    public Category saveCategory(String email, Category category);
}
