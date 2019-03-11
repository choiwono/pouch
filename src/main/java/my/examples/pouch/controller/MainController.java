package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final AccountService accountService;

    @GetMapping(value="/")
    public String index(Model model){
        return "index";
    }

    @GetMapping(value="/main")
    public String main(Model model){
        return "index";
    }
}
