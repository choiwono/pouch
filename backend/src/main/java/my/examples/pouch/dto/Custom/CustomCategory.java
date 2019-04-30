package my.examples.pouch.dto.Custom;

import lombok.Data;

import java.util.List;

@Data
public class CustomCategory {
    private Long id;
    private String name;
    private List<CustomLink> links;
    private String nickName;
}

