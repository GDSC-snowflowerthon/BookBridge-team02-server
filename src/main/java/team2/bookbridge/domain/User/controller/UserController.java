package team2.bookbridge.domain.User.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import team2.bookbridge.domain.User.dto.UserSigninRequestDto;
import team2.bookbridge.domain.User.service.UserService;
import team2.bookbridge.domain.enums.Role;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody  UserSigninRequestDto requestDto){

        //중복 확인
        if(userService.checkIdDuplicate(requestDto.getId())){
            return "중복된 아이디입니다.";
        }

        //기부자/수혜자 구분
        if(requestDto.getRegistation_number() == "")
            userService.signup(requestDto, Role.DONOR);

        userService.signup(requestDto,Role.RECIPIENT);

        return "회원 가입 성공";
    }
}
