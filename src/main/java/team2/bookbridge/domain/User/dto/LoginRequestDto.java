package team2.bookbridge.domain.User.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String login_id;
    private String password;
}
