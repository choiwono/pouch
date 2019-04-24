package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.CustomLink;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import my.examples.pouch.service.serviceImpl.CustomTagDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
public class LinkController {
    private final LinkService linkService;
    private final TagService tagService;
    private final TagRepository tagRepository;
    //링크 추가하기
    @PostMapping
    public void addLink(){}

    // 링크 수정하기
    @PutMapping(value = "/{id}")
    public void editLink(@PathVariable(value="id") Long id){}

    //링크 삭제하기
    @DeleteMapping(value = "/{id}")
    public void deleteLink(@PathVariable(value="id") Long id){}

    //태그 이름에 해당하는 링크 가져오기
    @GetMapping
    public List<CustomLink> linksByTag(@RequestParam(name = "category-id") Long categoryId,
                                       @RequestParam(name = "tag-id") Long tagId){
        Tag tag = tagRepository.getOne(tagId);
        List<Link> links = linkService.getLinkByTagName(categoryId,tag.getTagName());
        return linkService.getCustomLinks(links);
    }
}

