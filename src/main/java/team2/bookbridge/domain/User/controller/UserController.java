package team2.bookbridge.domain.User.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import team2.bookbridge.domain.User.dto.*;
import team2.bookbridge.domain.User.service.UserService;
import team2.bookbridge.domain.enums.Role;

import java.util.List;
//import team2.bookbridge.global.auth.jwt.JwtTokenUtil;

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


    @GetMapping("/user/{user_id}")
    public MyPageResponseDto mypage(@PathVariable("user_id") Long user_id){
        return userService.mypage(user_id);
    }

    @GetMapping("/user/{user_id}/donation")
    public List<MyDonationResponseDto> myDonation(@PathVariable("user_id") Long user_id){
        return userService.myDonation(user_id);
    }

}
