package team2.bookbridge.domain.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team2.bookbridge.domain.User.dto.UserSignupRequestDto;
import team2.bookbridge.domain.User.dto.UserSignupResponseDto;
import team2.bookbridge.domain.User.service.UserService;
import team2.bookbridge.domain.enums.Role;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public UserSignupResponseDto signup(@RequestBody UserSignupRequestDto requestDto){
        //기부자/수혜자 구분
        if(requestDto.getRegistration_number().isEmpty())
            return userService.signup(requestDto, Role.DONOR);

        return userService.signup(requestDto,Role.RECIPIENT);
    }
}
