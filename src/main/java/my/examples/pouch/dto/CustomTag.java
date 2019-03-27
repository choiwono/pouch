package my.examples.pouch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

@Data
@AllArgsConstructor
public class CustomTag {
    private Long id;
    private String tagName;
    private int cnt;
}
