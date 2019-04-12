package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Link;
import my.examples.pouch.domain.Tag;
import my.examples.pouch.dto.*;
import my.examples.pouch.repository.TagRepository;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagApiController {
    private final LinkService linkService;
    private final TagService tagService;
    private final TagRepository tagRepository;

    @PostMapping("/search")
    public List<CustomLink> myCategory(@RequestBody TagItem tagItem,
                                            Principal principal){
        List<CustomLink> list = new ArrayList<>();
        List<Link> links = linkService.getLinkByTagName(tagItem.getCategoryId(),tagItem.getTagName());
        for(int i=0; i<links.size(); i++){
            CustomLink customLink = new CustomLink();
            customLink.setId(links.get(i).getId());
            customLink.setTitle(links.get(i).getTitle());
            customLink.setRegDate(links.get(i).getRegDate());
            Set<CustomTag> customTags = tagService.customTagsDto(links.get(i).getTags());
            customLink.setTags(customTags);
            list.add(customLink);
        }
        return list;
    }

    @GetMapping("/list")
    public ResponseDto<?> tagList(){
       ResponseDto<List<CustomTag>> resp = new ResponseDto<>();
       List<CustomTag> lists = new ArrayList<>();
       lists.add(new CustomTag(1L,"JAVA"));
       lists.add(new CustomTag(2L,"JAVA2"));
       lists.add(new CustomTag(3L,"SPRING"));
       resp.setResponse(lists);
       return resp;
    }

    @GetMapping(value="/{id}")
    public CustomTag checkEmail(@PathVariable(value="id") Long id){
        Tag tag = tagRepository.getOne(id);
        CustomTag customTag = new CustomTag(tag.getId(),tag.getTagName());
        return customTag;
    }

    @PostMapping("/add")
    public String addTagItem(@RequestBody CustomTagItem customTagItem,
                             Principal principal){
        String result = "success";
        Link link = linkService.getLinkById(customTagItem.getId());
        Set<Link> linkset = new HashSet<>();
        linkset.add(link);
        for(String s : customTagItem.getTags()){
            Tag tag = new Tag();
            tag.setLinks(linkset);
            tag.setTagName(s);
            tag.setEmail(principal.getName());
            tagRepository.save(tag);
        }
        return result;
    }
}
