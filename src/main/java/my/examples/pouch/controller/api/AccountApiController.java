package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;

    @PostMapping(value="/checkEmail/{id}")
    public String checkEmail(@PathVariable(value="id") String email){
        String result = "fail";
        Account account = accountService.findAccountByEmail(email);
        if(account == null){
            result = "success";
        } else {
            result = "duplicate";
        }
        return result;
    }
}