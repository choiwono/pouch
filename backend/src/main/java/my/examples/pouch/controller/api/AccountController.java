package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.dto.AccountInfo;
import my.examples.pouch.dto.Joinform;
import my.examples.pouch.dto.ResponseDto;
import my.examples.pouch.security.CustomSecurityUser;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AccountInfo> accountInfo(@AuthenticationPrincipal CustomSecurityUser customSecurityUser) {
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setId(customSecurityUser.getId());
        accountInfo.setEmail(customSecurityUser.getEmail());
        accountInfo.setRoles(customSecurityUser.getAuthorities());
        return new ResponseEntity<>(accountInfo, HttpStatus.OK);
    }

    //내 정보 가져오기
    @GetMapping(value = "/me")
    public ResponseEntity getMyAccount() {
        return new ResponseEntity(HttpStatus.OK);
    }

    //내 정보 수정하기
    @PutMapping(value = "/me")
    public void editMyAccount() {

    }

    //패스워드 찾기
    @PutMapping(value = "/findpswd")
    public ResponseEntity<ResponseDto> findPassword(String email) {
        Account account = accountService.findAccountByEmail(email);
        ResponseDto responseDto = new ResponseDto();

        // 요청한 이메일 계정이 있는 경우
        if (account != null) {
            String password = UUID.randomUUID().toString();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPasswd(passwordEncoder.encode(password));
            accountService.updateUserPassword(account);
            emailService.sendEmail(account, password);
                responseDto.setMessage("success");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        // 요청한 이메일 계정이 없는 경우
        } else {
            responseDto.setMessage("fail");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
    }

    //이메일 중복 확인하기
    @PostMapping(value = "/emailcheck")
    public ResponseEntity<ResponseDto> emailCheck(String email, Model model) {
        ResponseDto responseDto = new ResponseDto();
        Account emailCheck = accountService.findAccountByEmail(email);
        if (emailCheck == null) {
            responseDto.setMessage("success");
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } else {
            responseDto.setMessage("duplicate");
            return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
        }
    }


    //회원가입
    @PostMapping(value = "/join")
    public ResponseEntity<Account> join(@Valid Joinform joinform,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account emailCheck = accountService.findAccountByEmail(joinform.getEmail());

        // 이메일이 중복되지 않는 경우
        if (emailCheck == null) {
            Account account = new Account();
            account.setName(joinform.getName());
            account.setEmail(joinform.getEmail());
            account.setNickName(joinform.getNickname());
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            account.setPasswd(passwordEncoder.encode(joinform.getPasswd()));
            accountService.join(account);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        // 이메일이 중복되는 경우 (409 : 요청 충돌, CONFLICT)
        else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
