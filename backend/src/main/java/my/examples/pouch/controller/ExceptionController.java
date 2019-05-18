package my.examples.pouch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExceptionController {

    @GetMapping("/categories/**")
    public String category(){
        return "index";
    }
}
