package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoryService categoryService;

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
}
