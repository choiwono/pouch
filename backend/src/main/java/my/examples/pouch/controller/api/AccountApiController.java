package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.repository.CategoryRepository;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

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

    @PostMapping(value="/category/{id}")
    public List<CustomCategory> myCategory(@PathVariable(value="id") Long id,
                                           Principal principal){

        List<CustomCategory> list = new ArrayList<>();
        List<Category> categories = categoryService.findMyCategoryList(principal.getName());
        for(int i=0; i<categories.size(); i++){
            CustomCategory customCategory = new CustomCategory();
            customCategory.setId(categories.get(i).getId());
            customCategory.setName(categories.get(i).getCategoryName());
            list.add(customCategory);
        }
        return list;
    }
}