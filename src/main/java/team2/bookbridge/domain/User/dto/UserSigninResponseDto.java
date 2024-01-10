package team2.bookbridge.domain.User.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSigninResponseDto {
    private Long user_id;
    private String id;

    @Builder
    public UserSigninResponseDto(Long user_id, String id){
        this.user_id = user_id;
        this.id = id;
    }
}
