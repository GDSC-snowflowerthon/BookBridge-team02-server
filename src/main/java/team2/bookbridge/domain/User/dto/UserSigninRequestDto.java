package team2.bookbridge.domain.User.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.enums.Role;


@Getter @Setter
@NoArgsConstructor
public class UserSigninRequestDto {

    @NotBlank(message = "로그인 아이디가 비어있습니다.")
    private String id;

    @NotBlank(message = "비밀번호가 비어있습니다.")
    private String password;

    @NotBlank(message = "비밀번호 확인란이 비어있습니다.")
    private String check_password;

    @NotBlank(message = "이메일 주소가 비어있습니다.")
    private String email;

    @NotBlank(message = "이메일 주소가 비어있습니다.")
    private String name;

    private String registation_number;

    // 비밀번호 암호화
    public User toEntity(String encodedPassword, Role role) {
        return User.builder()
                .id(this.id)
                .password(encodedPassword)
                .name(this.name)
                .email(this.email)
                .registration_number(this.registation_number)
                .role(role)
                .build();
    }


}
