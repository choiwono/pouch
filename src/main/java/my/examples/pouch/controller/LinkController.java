package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.repository.LinkRepository;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final LinkRepository linkRepository;

    @GetMapping(value = "/view/{id}")
    public String selectCategory(@PathVariable(value = "id") Long categoryId,
                                 Principal principal,
                                 Model model) {
        List<Link> links = linkService.getMyPouchByCategory(categoryId, principal.getName());
        List<Category> categories = categoryService.findMyCategoryList(principal.getName());

        List<Tag> tags = tagService.findMyTagListByAccountId(principal.getName(), categoryId);

        model.addAttribute("links",links);
        model.addAttribute("categories",categories);
        model.addAttribute("selectCategory",categoryId);
        model.addAttribute("tags",tags);

        return "/pouch/list";
    }

    @PostMapping(value = "/changeTitle")
    public String changeTitle(Long id, String title) {

        Link link = linkService.getLinkById(id);
        link.setId(id);
        link.setTitle(title);
        linkRepository.save(link);

        Long categoryId = link.getCategory().getId();

        return "redirect:/link/view/"+categoryId;
    }

    @PostMapping(value = "/deleteLink")
    public String deleteLink(Long id){
        Link link = linkService.getLinkById(id);
        linkRepository.delete(link);
        Long categoryId = link.getCategory().getId();

        return "redirect:/link/view/"+categoryId;

    }

}