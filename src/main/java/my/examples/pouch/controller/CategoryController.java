package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @PostMapping("/add")
    public String addCategory(@RequestParam String categoryName,
                              Principal principal) throws Exception {

            Account account = accountService.findAccountByEmail(principal.getName());
            List<Category> categories = categoryService.findMyCategoryList(principal.getName());
            List<Integer> list = new ArrayList<>();
            for(int i=0; i<categories.size(); i++){
                list.add(categories.get(i).getOrdering());
            }
            Collections.reverse(list);
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setOrdering(list.get(0)+1);
            category.setAccount(account);

            categoryRepository.save(category);

            return "redirect:/main";

    }
}
