package my.examples.pouch.dto;

import lombok.Data;
import java.util.*;

@Data
public class CustomTag {
    private Long id;
    private String email;
    private String tagName;
    private int options;
    private Date regDate;
}
