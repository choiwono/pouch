package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Account;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Message;
import my.examples.pouch.dto.ResponseDto;
import my.examples.pouch.dto.custom.CustomCategory;
import my.examples.pouch.service.AccountService;
import my.examples.pouch.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;
    private final AccountService accountService;

    //검색해서 카테고리 목록 가져오기
    @GetMapping(value = "/search")
    public ResponseEntity<List<CustomCategory>> searchCategories(@RequestParam(name = "searchType") int searchType,
                                                                 @RequestParam(name = "searchStr") String searchStr) {
        List<Category> categories = categoryService.getCategoriesBySearch(searchType, searchStr);
        List<CustomCategory> list = categoryService.getCustomCategories(categories);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    //특정 유저의 카테고리 목록 가져오기
    @GetMapping
    public ResponseEntity<List<CustomCategory>> getCategoryByAccount(@RequestParam(name = "email") String email) {
        List<Category> categories = categoryService.findMyCategoryList(email);
        List<CustomCategory> list = categoryService.getCustomCategories(categories);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //특정 카테고리 가져오기
    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomCategory> getCategory(@PathVariable(value = "id") Long id) {
        Category category = categoryService.getCategory(id);
        CustomCategory customCategory = categoryService.getCustomCategory(category);
        return new ResponseEntity<>(customCategory,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addCategory(@RequestParam(name = "name") String name,
                                Principal principal) {
        Account account = accountService.findAccountByEmail(principal.getName());
        Category category = new Category();
        category.setCategoryName(name);
        category.setAccount(account);

        ResponseDto responseDto = new ResponseDto();
        if(categoryService.addCategory(category) != null){
            responseDto.setMessage("OK, created");
            return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        } else {
            responseDto.setMessage("Error, not created");
            return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
        }
    }

    //카테고리 수정 TODO 작업 전
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> editCategory(@PathVariable(value = "id") Long id) {
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    //카테고리 삭제 TODO 작업 전
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteCategory(@PathVariable(value = "id") Long id) {
        categoryService.deleteCategory(id);
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }

    // 다른 유저의 카테고리를 복사해서 내 카테고리로 저장하기
    @PostMapping(value = "/copy")
    public ResponseEntity<ResponseDto> saveCategory(@RequestParam(name = "id") Long id, Principal principal) {
        Category category = categoryService.getCategory(id);
        ResponseDto responseDto = new ResponseDto();
        if(categoryService.saveCategory(principal.getName(),category) == null){
            responseDto.setMessage("error, not Created");
            return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
        } else {
            responseDto.setMessage("OK, Created");
            return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
        }
    }

    // 다른 유저에게 내 카테고리 보내기
    @PostMapping(value = "/send")
    public ResponseEntity<ResponseDto> sendCategory(@RequestParam(name="id")Long id,
                                 @RequestParam(name="email") String email){
        Message message = new Message();
        message.setReceiveId(email);
        message.setCategoryId(id);
        message.setUseYn("N");


        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
    }
}

