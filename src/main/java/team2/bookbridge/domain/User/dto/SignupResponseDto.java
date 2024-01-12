package team2.bookbridge.domain.User.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupResponseDto {
    private Long user_id;
    private String login_id;

    @Builder
    public SignupResponseDto(Long user_id, String loginId){
        this.user_id = user_id;
        this.login_id = loginId;
    }
}
