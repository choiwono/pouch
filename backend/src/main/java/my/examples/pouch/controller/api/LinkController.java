package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.custom.CustomLink;
import my.examples.pouch.dto.ResponseDto;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;
    private final TagService tagService;

    //링크 추가하기
    @PostMapping
    public ResponseEntity<ResponseDto> addLink(){
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.CREATED);
    }

    // 링크 수정하기
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> editLink(@PathVariable(value="id") Long id){
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    //링크 삭제하기
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteLink(@PathVariable(value="id") Long id){
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    //태그 이름에 해당하는 링크 가져오기
    @GetMapping
    public ResponseEntity<List<CustomLink>> linksByTag(@RequestParam(name = "category-id") Long categoryId,
                                       @RequestParam(name = "tag-id") Long tagId){
        Tag tag = tagService.getTagById(tagId);
        List<CustomLink> list = linkService.getLinkByTagName(categoryId,tag.getTagName());
        return new ResponseEntity<List<CustomLink>>(list,HttpStatus.OK);
    }
}

