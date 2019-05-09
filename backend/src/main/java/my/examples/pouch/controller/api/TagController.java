package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.Custom.CustomTag;
import my.examples.pouch.dto.Custom.CustomTagItem;
import my.examples.pouch.dto.ResponseDto;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {
    private final LinkService linkService;
    private final TagService tagService;

    //태그 추가
    @PostMapping
    public ResponseEntity<ResponseDto> addTagItem(CustomTagItem customTagItem,
                                                  Principal principal){
        Link link = linkService.getLinkById(customTagItem.getId());
        tagService.saveTags(link,customTagItem,principal.getName());
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    //태그 수정
    @PutMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> editTag(@PathVariable(value="id") Long id){
        ResponseDto responseDto = new ResponseDto();
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    //태그 삭제
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResponseDto> deleteTag(@PathVariable(value = "id") Long id){
        ResponseDto responseDto = new ResponseDto();
        tagService.deleteTag(tagService.getTagById(id));
        return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<List<CustomTag>> getTagsByLink(@PathVariable(value = "id") Long id){
        List<CustomTag> list = tagService.findTagListByLinksId(id);
        return new ResponseEntity<List<CustomTag>>(list,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomTagDto>> getTags(@RequestParam(value="category-id") Long id){
        List<CustomTagDto> list = tagService.findTagListByCategoryId(id);
        return new ResponseEntity<List<CustomTagDto>>(list,HttpStatus.OK);
    }
}
