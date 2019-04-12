package my.examples.pouch.controller;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.*;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.service.*;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping("/login")
    public String login(
            @RequestParam(name = "fail",
                    required = false, defaultValue = "false") String errorFlag,
            Model model) {
        model.addAttribute("errorFlag", errorFlag);
        return "account/login";
    }

    @GetMapping("/join")
    public String joinform() {
        return "account/join";
    }

    @PostMapping("/join")
    public String joinform(@Valid Joinform joinform,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "account/joinfalse";
        }

        if (!joinform.getPasswd().equals(joinform.getPasswd2())) {
            model.addAttribute("notEqualPassword", "비밀번호가 서로 일치하지않습니다.");
            return "account/joinfalse";
        }

        Account emailCheck = accountService.findAccountByEmail(joinform.getEmail());
        if (emailCheck != null) {
            model.addAttribute("duplicateEmail", "중복된 이메일이 존재합니다.");
            return "account/joinfalse";
        }

        Account account = new Account();
        account.setEmail(joinform.getEmail());
        account.setName(joinform.getName());
        account.setNickName(joinform.getNickname());
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        account.setPasswd(passwordEncoder.encode(joinform.getPasswd()));
        Account result = accountService.join(account);
        return "/account/welcome";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "searchType") int searchType,
                         @RequestParam(name = "searchStr") String searchStr, Model model) {
        List<Category> categoryList = new ArrayList<>();
        long count = 0;
        if (searchType == 1) {
            categoryList = categoryRepository.searchCategory(searchStr);
            count = categoryRepository.countSearchCategory(searchStr);
        } if (searchType == 2) {
            categoryList = categoryRepository.searchTag(searchStr);
            count = categoryRepository.countSearchTag(searchStr);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("count", count);

        return "account/search";
    }
}
