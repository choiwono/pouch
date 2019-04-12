package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final LinkService linkService;
    private final TagService tagService;

    @PostMapping("/add")
    public String addCategory(@RequestParam String categoryName,
                              Principal principal) throws Exception {

        Account account = accountService.findAccountByEmail(principal.getName());
        List<Category> categories = categoryService.findMyCategoryList(principal.getName());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            list.add(categories.get(i).getOrdering());
        }
        Collections.reverse(list);
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setOrdering(list.get(0) + 1);
        category.setAccount(account);

        categoryRepository.save(category);

        return "redirect:/main";
    }

    @GetMapping("/view/{id}")
    public String viewCategory(@PathVariable(value = "id") Long categoryId,
                               Model model) throws Exception {

        List<Link> links = linkService.getSearchPouchByCategory(categoryId);
        List<CustomTagDto> tags = tagService.findTagListByCategoryId(categoryId);

        model.addAttribute("links", links);
        model.addAttribute("tags", tags);

        return "/pouch/searchList";
    }

}
