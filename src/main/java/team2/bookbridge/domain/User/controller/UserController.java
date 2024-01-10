package team2.bookbridge.domain.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team2.bookbridge.domain.User.dto.LoginRequestDto;
import team2.bookbridge.domain.User.dto.LoginResponseDto;
import team2.bookbridge.domain.User.dto.SignupRequestDto;
import team2.bookbridge.domain.User.dto.SignupResponseDto;
import team2.bookbridge.domain.User.service.UserService;
import team2.bookbridge.domain.enums.Role;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public SignupResponseDto signup(@RequestBody SignupRequestDto requestDto){
        //기부자/수혜자 구분
        if(requestDto.getRegistration_number().isEmpty())
            return userService.signup(requestDto, Role.DONOR);

        return userService.signup(requestDto,Role.RECIPIENT);
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
        return userService.login(requestDto);
    }
}
