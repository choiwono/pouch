package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;

    /*기존 코드
    @GetMapping(value="/{id}")
    public List<CustomCategory> myCategory(@PathVariable(value="id") Long id
            ,Principal principal){
        List<CustomCategory> list = new ArrayList<>();
        List<Category> categories = categoryService.findMyCategoryList(id);
        for(int i=0; i<categories.size(); i++){
            CustomCategory customCategory = new CustomCategory();
            customCategory.setId(categories.get(i).getId());
            customCategory.setName(categories.get(i).getCategoryName());
            list.add(customCategory);
        }
        return list;
    }
    */

    //검색해서 카테고리 목록 가져오기
    @GetMapping(value = "/search")
    public List<CustomCategory> searchCategories(@RequestParam(name = "searchType") int searchType,
                         @RequestParam(name = "searchStr") String searchStr, Model model) {
        List<CustomCategory> list = new ArrayList<>();
        return list;
    }

    //특정 유저의 카테고리 목록 가져오기
    @GetMapping
    public void getCategoryByAccount(@RequestParam(name = "accountId") Long accountId) {}

    //특정 카테고리 가져오기
    @GetMapping(value = "/{id}")
    public void getCategory(@PathVariable(value="id") Long id){}



   //카테고리 수정하기
    @PutMapping(value = "/{id}")
    public void editCategory(@PathVariable(value="id") Long id){

    }

    //카테고리 삭제
    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable(value = "id") Long id){

    }

    // 다른 유저의 카테고리를 복사해서 내 카테고리로 저장하기
    @PostMapping
    public void shareCategory(@RequestParam(name = "id") Long id){

    }
}
