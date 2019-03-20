package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public String addCategory(@RequestParam String categoryName, HttpSession session) {
        String result = "fail";

        if (session.getAttribute("category") == null) {
            Category category = new Category();
            category.setCategoryName(categoryName);
            category.setOrdering(0);
            categoryRepository.save(category);
            result = "success";
        }
        return result;
    }
}
