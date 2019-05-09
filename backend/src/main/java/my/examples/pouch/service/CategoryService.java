package my.examples.pouch.service;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.Custom.CustomCategory;
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
    private final LinkService linkService;
    private final AccountService accountService;

    @Transactional
    public Category getCategory(Long id) {
        return categoryRepository.getCategories(id);
    }

    public List<Category> findMyCategoryList(String email) {
        return categoryRepository.findMyCategoryByEmail(email);
    }


    final static int SEARCH_BY_CATEGORY = 1;
    final static int SEARCH_BY_TAG = 2;

    public List<Category> getCategoriesBySearch(int searchType, String searchStr) {

        if (searchType == SEARCH_BY_CATEGORY) {
            return categoryRepository.searchCategory(searchStr);
        } else {
            return categoryRepository.searchTag(searchStr);
        }
    }

    public List<CustomCategory> getCustomCategories(List<Category> categories){
        List<CustomCategory> customCategories = new ArrayList<>();
        for(int i=0; i<categories.size(); i++){
            CustomCategory customCategory = new CustomCategory();
            customCategory.setId(categories.get(i).getId());
            customCategory.setName(categories.get(i).getCategoryName());
            customCategory.setNickName(categories.get(i).getAccount().getNickName());
            customCategories.add(customCategory);
        }
        return customCategories;
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category saveCategory(Category sharedCategory) {
        return categoryRepository.save(sharedCategory);
    }

    public void deleteCategory(Long id){
        Category category = categoryRepository.getOne(id);
        List<Link> links = category.getLinks();
        for(int i=0; i<links.size(); i++){
            linkService.deleteTagMappingByLinkId(links.get(i).getId());
            linkService.deleteLink(links.get(i).getId());
        }
        categoryRepository.deleteByCategoryId(id);
    }

    public CustomCategory getCustomCategory(Category category) {
        CustomCategory customCategory = new CustomCategory();
        customCategory.setId(category.getId());
        customCategory.setNickName(category.getAccount().getNickName());
        customCategory.setName(category.getCategoryName());
        customCategory.setLinks(linkService.getCustomLinks(category.getLinks()));
        customCategory.setEmail(category.getAccount().getEmail());
        return customCategory;
    }

    public Category shareCategory(String email, Category category) {
        Category copyCategory = new Category();
        Account account = accountService.findAccountByEmail(email);
        copyCategory.setAccount(account);
        copyCategory.setCategoryName(category.getCategoryName());
        Category shareCategory = saveCategory(copyCategory);
        for (Link link : category.getLinks()) {
            linkService.share(link, account,shareCategory.getId());
        }
        return shareCategory;
    }
}
