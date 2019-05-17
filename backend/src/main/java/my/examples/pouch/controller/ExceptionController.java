package my.examples.pouch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.NotContextException;

@Controller
public class ExceptionController {

    @GetMapping("/categories/**")
    public String category(){
        return "index";
    }
}
