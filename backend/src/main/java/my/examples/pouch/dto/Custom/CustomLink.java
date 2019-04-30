package my.examples.pouch.dto.Custom;

import lombok.Data;
import my.examples.pouch.service.serviceImpl.CustomTagDto;

import java.util.*;

@Data
public class CustomLink {
    private Long id;
    private String title;
    private String tagName;
    private String url;
    private Date regDate;
    Set<CustomTag> tags;
    List<CustomTagDto> tagDtos;
    public CustomLink(){
        tagDtos = new ArrayList<>();
        tags = new HashSet<>();
    }
}
