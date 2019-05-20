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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final EmailService emailService;

    // 계정 토큰 발급
    @Secured({"ROLE_USER","ROLE_ADMIN"})
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
    public ResponseEntity<ResponseDto> getMyAccount() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("OK, success");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    //내 정보 수정하기
    @PutMapping(value = "/me")
    public ResponseEntity<ResponseDto> editMyAccount() {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("OK, success");
        return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.OK);
    }

    //패스워드 찾기
    @PutMapping(value = "/findpswd")
    public ResponseEntity<ResponseDto> findPassword(String email) {
        // 옵셔널 객체를 만들면 좋다. 자바 8 문법을 적극적으로 사용하는 것
        Account account = accountService.findAccountByEmail(email);

        ResponseDto responseDto = new ResponseDto();
        // 요청한 이메일 계정이 있는 경우
        if (account != null) {
            emailService.sendEmail(account, accountService.updateUserPassword(account));
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
    public ResponseEntity<ResponseDto> join(@Valid Joinform joinform,
                                        BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Account emailCheck = accountService.findAccountByEmail(joinform.getEmail());
        ResponseDto responseDto = new ResponseDto();
        // 이메일이 중복되지 않는 경우
        if (emailCheck == null) {
            accountService.join(joinform);
            responseDto.setMessage("OK, Created");
            return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CREATED);
        }
        // 이메일이 중복되는 경우 (409 : 요청 충돌, CONFLICT)
        else {
            responseDto.setMessage("Error, duplicated");
            return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.CONFLICT);
        }
    }
}
