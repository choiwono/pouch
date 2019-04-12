package my.examples.pouch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

@Data
public class CustomTag {
    private Long id;
    private String tagName;

    public CustomTag(Long id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }
}
