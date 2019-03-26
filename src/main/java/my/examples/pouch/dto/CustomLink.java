package my.examples.pouch.dto;

import lombok.Data;
import java.util.*;

@Data
public class CustomLink {
    private Long id;
    private String title;
    private Date regDate;
    Set<CustomTag> tags;

    public CustomLink(){
        tags = new HashSet<>();
    }
}
