package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.dto.AccountInfo;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.security.CustomSecurityUser;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.EmailService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final EmailService emailService;

    // 계정 토큰 발급
    @PostMapping(value = "/auth")
    public AccountInfo accountInfo(@AuthenticationPrincipal CustomSecurityUser customSecurityUser) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(customSecurityUser.getId());
        accountInfo.setEmail(customSecurityUser.getEmail());
        accountInfo.setRoles(customSecurityUser.getAuthorities());
        return accountInfo;
    }

    //내 정보 가져오기
    @GetMapping(value = "/me")
    public void getMyAccount() {
    }

    //내 정보 수정하기
    @PutMapping(value = "/me")
    public void editMyAccount() {
    }

    //패스워드 찾기
    @PutMapping(value = "/findpswd")
    public String findPassword(String email) {
        String result = "fail";
        Account account = accountService.findAccountByEmail(email);
        if(account!=null) {
            String password = UUID.randomUUID().toString();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPasswd(passwordEncoder.encode(password));
            accountService.updateUserPassword(account);
            emailService.sendEmail(account, password);
            result = "success";
            System.out.println(result);
        }else{
            result = "fail";
            System.out.println(result);
        }
        return result;
    }

    //이메일 중복 확인하기
    @PostMapping(value = "/emailcheck")
    public String emailCheck(String email, Model model) {
        Account emailCheck = accountService.findAccountByEmail(email);
        String result = "fail";
        if(emailCheck == null){
            result = "success";
        } else {
            result = "duplicate";
        }
        return result;
    }


    //회원가입
    @PostMapping(value = "/join")
    public Account join(@Valid Joinform joinform,
                        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("---"); }

        Account emailCheck = accountService.findAccountByEmail(joinform.getEmail());
        if(emailCheck == null){
            Account account = new Account();
            account.setName(joinform.getName());
            account.setEmail(joinform.getEmail());
            account.setNickName(joinform.getNickname());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPasswd(passwordEncoder.encode(joinform.getPasswd()));
            return accountService.join(account);
        } else {
            return null;
        }
    }
}
