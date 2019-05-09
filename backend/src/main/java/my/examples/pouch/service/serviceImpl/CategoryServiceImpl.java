package my.examples.pouch.service.serviceImpl;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.dto.custom.CustomCategory;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final LinkService linkService;
    private final AccountService accountService;

    final static int SEARCH_BY_CATEGORY = 1;
    final static int SEARCH_BY_TAG = 2;

    @Override
    @Transactional(readOnly = true)
    public Category getCategory(Long id) {
        return categoryRepository.getCategories(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findMyCategoryList(String email) {
        return categoryRepository.findMyCategoryByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> getCategoriesBySearch(int searchType, String searchStr){
        List<Category> list = new ArrayList<>();
        if(searchType == SEARCH_BY_CATEGORY) {
            list = categoryRepository.searchCategory(searchStr);
        } else if(searchType == SEARCH_BY_TAG){
            list = categoryRepository.searchTag(searchStr);
        }
        return list;
    }

    @Override
    public List<CustomCategory> getCustomCategories(List<Category> categories) {
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

    @Override
    @Transactional
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Transactional
    public Category saveCategory(Category sharedCategory) {
        return categoryRepository.save(sharedCategory);
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.getOne(id);
        List<Link> links = category.getLinks();
        for(int i=0; i<links.size(); i++){
            linkService.deleteTagMappingByLinkId(links.get(i).getId());
            linkService.deleteLink(links.get(i).getId());
        }
        categoryRepository.deleteByCategoryId(id);
    }

    @Override
    public CustomCategory getCustomCategory(Category category) {
        CustomCategory customCategory = new CustomCategory();
        customCategory.setId(category.getId());
        customCategory.setNickName(category.getAccount().getNickName());
        customCategory.setName(category.getCategoryName());
        customCategory.setLinks(linkService.getCustomLinks(category.getLinks()));
        customCategory.setEmail(category.getAccount().getEmail());
        return customCategory;
    }

    @Override
    @Transactional
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
