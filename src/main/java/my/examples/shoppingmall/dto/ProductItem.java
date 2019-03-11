package my.examples.shoppingmall.dto;

import lombok.Data;
import my.examples.shoppingmall.domain.FileImage;

import java.util.Date;
import java.util.List;

@Data
public class ProductItem {
    private Long id;
    private String name;
    private int amount;
    private int price;
    private Double rating;
    private Date regDate;
    private List<FileImage> files;
}
