package my.examples.pouch.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomCategory {
    private Long id;
    private String name;
    private List<CustomLink> links;
}

