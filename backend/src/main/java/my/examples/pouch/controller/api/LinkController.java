package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.ResponseDto;
import my.examples.pouch.dto.custom.CustomLink;
import my.examples.pouch.service.CategoryService;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
public class LinkController {
    private final CategoryService categoryService;
    private final LinkService linkService;
    private final TagService tagService;

    // 링크 수정하기 TODO
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> editLink(@PathVariable(value="id") Long id){
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //링크 삭제하기 TODO
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteLink(@PathVariable(value="id") Long id){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("OK, success");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    //태그 이름에 해당하는 링크 가져오기
    @GetMapping
    public ResponseEntity<List<CustomLink>> linksByTag(@RequestParam(name = "category-id") Long categoryId,
                                       @RequestParam(name = "tag-id") Long tagId){
        Tag tag = tagService.getTagById(tagId);
        List<CustomLink> list = linkService.getLinkByTagName(categoryId,tag.getTagName());
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    // 다른 유저의 링크를 복사해서 내 카테고리에 저장하기
    @PostMapping(value = "/copy")
    public ResponseEntity<ResponseDto> saveLink(@RequestParam(name = "id") Long id,
                                                @RequestParam(name = "categoryId") Long categoryId, Principal principal) {
        linkService.getLinkById(id);
//      Category category = categoryService.getCategory(id);
        ResponseDto responseDto = new ResponseDto();
//        if(categoryService.saveCategory(principal.getName(),category) == null){
//            responseDto.setMessage("error, not Created");
//            return new ResponseEntity<ResponseDto>(responseDto,HttpStatus.BAD_REQUEST);
//        } else {
//            responseDto.setMessage("OK, Created");
            return new ResponseEntity<>(responseDto,HttpStatus.CREATED);
//        }
    }
}

