package my.examples.pouch.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class Joinform {
    @NotEmpty(message="이름을 입력해주세요")
    @Size(min=2, max=10)
    private String name;

    @NotEmpty(message="닉네임을 입력해주세요")
    @Size(min=2, max=10)
    private String nickname;

    @NotEmpty(message="이메일을 작성해주세요")
    @Email(message="메일의 양식을 잘 지켜주세요")
    @Size(min=4, max=50)
    private String email;

    @NotEmpty(message="비밀번호를 입력해주세요")
    @Size(min=4, max=10)
    private String passwd;
}
