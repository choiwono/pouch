package my.examples.pouch.controller.api;

import lombok.RequiredArgsConstructor;
import my.examples.pouch.domain.Category;
import my.examples.pouch.domain.Link;
import my.examples.pouch.dto.CustomCategory;
import my.examples.pouch.dto.CustomLink;
import my.examples.pouch.dto.CustomTag;
import my.examples.pouch.dto.TagItem;
import my.examples.pouch.service.LinkService;
import my.examples.pouch.service.TagService;
import org.springframework.stereotype.Controller;
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

}
