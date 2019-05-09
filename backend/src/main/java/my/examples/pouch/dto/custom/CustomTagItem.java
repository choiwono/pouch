package my.examples.pouch.dto.custom;

import lombok.Data;

import java.util.List;

@Data
public class CustomTagItem {
    private Long id;
    private List<String> tags;
}
