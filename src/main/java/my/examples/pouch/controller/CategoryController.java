package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final AccountService accountService;

    @PostMapping("/add")
    public String addCategory(@RequestParam String categoryName,
                              Principal principal,
                              HttpSession session) throws Exception {
        String result = "fail";

        if (session.getAttribute("category") == null) {

            Account account = accountService.findAccountByEmail(principal.getName());

            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setOrdering(0);

            categoryRepository.save(category);
            result = "success";
        }
        return result;
    }
}
