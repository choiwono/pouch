import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AccountService accountService;
    private final CategoryService categoryService;

    @GetMapping(value="/")
    public String index(Model model){
        return "index";
    }

    @GetMapping(value="/main")
    public String main(Model model,
                       Principal principal){
        if(principal != null){
            List<Category> categories = categoryService.findMyCategoryList(principal.getName());
            model.addAttribute("categories",categories);
        }
        return "index";
    }
}