package team2.bookbridge.domain.User.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.User.dto.UserSignupRequestDto;
import team2.bookbridge.domain.User.dto.UserSignupResponseDto;
import team2.bookbridge.domain.User.repositiry.UserRepository;
import team2.bookbridge.domain.enums.Role;
import team2.bookbridge.global.common.exception.ConflictException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

     private final BCryptPasswordEncoder encoder;

    public void checkIdDuplicate(String id) {
        if (userRepository.existsById(id)) {
            throw new ConflictException("이미 사용중인 아이디입니다."){};
        }
    }

    //회원가입
    public UserSignupResponseDto signup(UserSignupRequestDto requestDto, Role role) {
        //중복 확인
        checkIdDuplicate(requestDto.getId());
        //비밀번호 암호화
        User user = userRepository.save(requestDto.toEntity(encoder.encode(requestDto.getPassword()), role));

        return UserSignupResponseDto.builder()
                .user_id(user.getUser_id())
                .id(user.getId())
                .build();

    }


}
