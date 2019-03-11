package my.examples.shoppingmall.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class Joinform {
    @NotEmpty(message="이름을 입력해주세요")
    @Size(min=3, max=12)
    private String name;
    @NotEmpty(message="닉네임을 입력해주세요")
    @Size(min=4, max=12)
    private String nickname;
    @NotEmpty(message="이메일을 작성해주세요")
    @Email(message="메일의 양식을 잘 지켜주세요")
    @Size(min=4, max=255)
    private String email;
    @NotEmpty(message="비밀번호를 입력해주세요")
    @Size(min=4, max=12)
    private String passwd;
    @NotEmpty(message="비밀번호 확인을 입력해주세요")
    @Size(min=4, max=12)
    private String passwd2;
}
