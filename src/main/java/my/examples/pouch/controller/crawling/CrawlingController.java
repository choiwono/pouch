package my.examples.pouch.controller.crawling;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.AccountCategory;
import my.examples.pouch.domain.Board;
import my.examples.pouch.repository.BoardRepository;
import my.examples.pouch.service.AccountCategoryService;
import my.examples.pouch.service.AccountService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final AccountCategoryService accountCategoryService;

    @PostMapping("/save")
    public String crawling(@RequestParam String url,
                           Principal principal) throws Exception{
        Document doc = Jsoup.connect(url).get();
        Elements title = doc.select("title");
        Elements sub = doc.select("meta[name=description]");
        String content = title.html();
        if(content == null){
            content = sub.attr("content");
        }
        AccountCategory accountCategory = accountCategoryService.getAccountCategory(1L);
        Account account = accountService.findAccountByEmail(principal.getName());
        Board board = new Board();
        board.setTitle(content);
        board.setUrl(url);
        board.setEmail(principal.getName());
        board.setBoardOption(0L);
        board.setRepository(account.getId());
        board.setAccountCategory(accountCategory);
        board.setAccount(account);
        boardRepository.save(board);
        return "index";
    }
}
