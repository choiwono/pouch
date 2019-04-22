package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.AccountInfo;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.security.CustomSecurityUser;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.AccountService;
import org.hibernate.annotations.JoinFormula;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // 계정 토큰 발급
    @PostMapping(value="/auth")
    public AccountInfo accountInfo(@AuthenticationPrincipal CustomSecurityUser customSecurityUser){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(customSecurityUser.getId());
        accountInfo.setEmail(customSecurityUser.getEmail());
        accountInfo.setRoles(customSecurityUser.getAuthorities());
        return accountInfo;
    }

    //내 정보 가져오기
    @GetMapping(value = "/me")
    public void getMyAccount(){
    }

    //내 정보 수정하기
    @PutMapping(value = "/me")
    public void editMyAccount(){}

    //패스워드 찾기
    @PutMapping(value = "/findpswd")
    public void findPassword(){}

    //이메일 중복 확인하기
    @PostMapping(value = "/emailcheck")
    public void emailCheck(){}

    //로그인
    @PostMapping(value = "/login")
    public void login(){}

    //로그아웃
    @GetMapping(value = "/logout")
    public void logout(){}

    //회원가입
    @PostMapping(value = "/join")
    public void join(@Valid Joinform joinform, BindingResult bindingResult, Model model){


    }

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
        accountService.join(account);
        return "/account/welcome";

    }
}
