package my.examples.pouch.controller.crawling;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/crawling")
@RequiredArgsConstructor
public class CrawlingController {

    @PostMapping("/test")
    public String crawling(@RequestParam String url) throws Exception{
        Document doc = Jsoup.connect(url).get();
        Elements title = doc.select(".dropdown--item_excerpt");
        System.out.println(title);
        return "index";
    }
}
