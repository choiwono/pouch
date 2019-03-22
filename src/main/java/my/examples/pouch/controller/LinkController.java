package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final TagService tagService;

    @GetMapping(value = "/view/{id}")
    public String selectCategory(@PathVariable(value="id") Long categoryId,
                                 Principal principal,
                                 Model model){
        List<Link> links = linkService.getMyPouchByCategory(categoryId,principal.getName());
        List<Category> categories = categoryService.findMyCategoryList(principal.getName());
        //Account account = accountService.findAccountByEmail(principal.getName());
        List<Tag> tags = tagService.findMyTagListByAccountId(principal.getName());
        //System.out.println(account.getId());

        model.addAttribute("links",links);
        model.addAttribute("categories",categories);
        model.addAttribute("selectCategory",categoryId);
        return "/pouch/list";
    }
}