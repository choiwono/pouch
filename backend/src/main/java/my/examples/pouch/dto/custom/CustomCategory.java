package my.examples.pouch.dto.custom;

import lombok.Data;

import java.util.List;

@Data
public class CustomCategory {
    private Long id;
    private String name;
    private List<CustomLink> links;
    private String nickName;
    private String email;

}

