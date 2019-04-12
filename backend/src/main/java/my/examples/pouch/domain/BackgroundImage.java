package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="background_image")
@Getter
@Setter
public class BackgroundImage {
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
}
