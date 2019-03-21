package my.examples.pouch.controller.crawling;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.repository.BoardRepository;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.AccountService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/crawling")
@RequiredArgsConstructor
public class CrawlingController {
    private final BoardRepository boardRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @PostMapping("/save")
    public String crawling(@RequestParam(required = true) String url,
                           @RequestParam(required = true) Long categoryId,
                           Principal principal) throws Exception{
        Document doc = Jsoup.connect(url).get();
        Elements title = doc.select("title");
        Elements sub = doc.select("meta[name=description]");
        String content = title.html();
        if(content == null){
            content = sub.attr("content");
        }
        Category category = categoryService.getAccountCategory(categoryId);
        Account account = accountService.findAccountByEmail(principal.getName());
        Link board = new Link();
        board.setTitle(content);
        board.setUrl(url);
        board.setEmail(principal.getName());
        board.setBoardOption(0L);
        board.setRepository(account.getId());
        board.setAccountCategory(category);
        board.setAccount(account);
        boardRepository.save(board);
        return "index";
    }
}