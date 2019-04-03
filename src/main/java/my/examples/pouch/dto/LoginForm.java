package my.examples.pouch.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {
    @NotBlank
    @Size(min=3, max = 60)
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
