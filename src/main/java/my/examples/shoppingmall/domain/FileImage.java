package my.examples.shoppingmall.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="file_image")
public class FileImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="file_name")
    private String fileName;
    private int options;
    @Column(name="save_file_name")
    private String saveFileName;

    @Column(name="mime_type")
    private String mimeType;

    private Long length;

    @Column(name="reg_date")
    private Date regDate;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public FileImage() {
        regDate = new Date();
    }
}
