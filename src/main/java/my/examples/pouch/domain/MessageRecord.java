package my.examples.pouch.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="message_record")
@Getter
@Setter
public class MessageRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accept = "N";

    @OneToOne
    @JoinColumn(name="message_id", referencedColumnName = "id", nullable = false)
    private Message message;
}
