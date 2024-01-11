package team2.bookbridge.domain.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.bookbridge.domain.enums.Role;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private Long user_id;
    private String id;
    private String name;
    private String email;
    private Role role;
}
