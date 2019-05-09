package my.examples.pouch.controller.crawling;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.repository.LinkRepository;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.AccountService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/crawling")
@RequiredArgsConstructor
public class CrawlingController {
    private final LinkRepository linkRepository;
    private final AccountService accountService;
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity crawling(@RequestParam(required = true) String url,
                                   @RequestParam(required = true) Long categoryId,
                                   Principal principal) throws Exception {
        // 타임아웃 설정
        Document doc = Jsoup.connect(url).timeout(5*1000).get();

        Elements top = doc.select("title");
        Elements sub = doc.select("meta[name=description]");
        Elements img = doc.select("meta[property=og:image]");

        String title = top.html();
        // 타이틀이 없을 경우 OR SUB 타이틀도 없을 경우에는 url로 대체 ( url 길이가 너무 길면 자르기 )
        if(title.isEmpty()) {
            title = sub.attr("content");
        } else if(sub.attr("content").isEmpty()){
            if(url.length() > 30){
                title = url.substring(0,30);
            } else {
                title = url;
            }
        }

        String imgSrc = img.attr("content");

        if(imgSrc.length() == 0){
            Elements str = doc.getElementsByTag("img");
            if(str.size() > 0){
                imgSrc = str.get(0).attr("src");
            }
        }

        Category category = categoryService.getCategory(categoryId);
        Account account = accountService.findAccountByEmail(principal.getName());
        Link link = new Link();

        link.setTitle(title);
        link.setUrl(url);
        link.setEmail(principal.getName());
        link.setSrc(imgSrc);
        link.setLinkOption(0L);
        link.setRepository(account.getId());
        link.setCategory(category);
        link.setAccount(account);
        linkRepository.save(link);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
