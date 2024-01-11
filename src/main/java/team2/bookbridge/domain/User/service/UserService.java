package team2.bookbridge.domain.User.service;
//
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.User.dto.LoginRequestDto;
import team2.bookbridge.domain.User.dto.LoginResponseDto;
import team2.bookbridge.domain.User.dto.SignupRequestDto;
import team2.bookbridge.domain.User.dto.SignupResponseDto;
import team2.bookbridge.domain.User.repositiry.UserRepository;
import team2.bookbridge.domain.enums.Role;
import team2.bookbridge.global.common.exception.ConflictException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//     private final BCryptPasswordEncoder encoder;


    //회원가입
    public SignupResponseDto signup(SignupRequestDto requestDto, Role role) {
        checkIdDuplicate(requestDto.getId());
        checkPasswordEqual(requestDto);

        User user = userRepository.save(requestDto.toEntity(requestDto.getPassword(), role));

        return SignupResponseDto.builder()
                .user_id(user.getUser_id())
                .id(user.getId())
                .build();

    }

    //로그인
    public LoginResponseDto login(LoginRequestDto requestDto){
        Optional<User> optionalUser = userRepository.findById(requestDto.getId());

        if(optionalUser.isEmpty()) {
            throw new ConflictException("존재하지 않는 아이디입니다."){};
        }

        User user = optionalUser.get();
        //if(!encoder.matches(requestDto.getPassword(), user.getPassword())){
        if(!requestDto.getPassword().equals(user.getPassword())){
            throw new ConflictException("잘못된 비밀번호입니다."){};
        }

//        // 로그인 성공 => Jwt Token 발급
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String secretKey = "bookbridgeSecertKey012341112";
//        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분
//
//        String jwtToken = JwtTokenUtil.createToken(user.getId(), key, expireTimeMs);

        return LoginResponseDto.builder()
                .user_id(user.getUser_id())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public void checkIdDuplicate(String id) {
        if (userRepository.existsById(id)) {
            throw new ConflictException("이미 사용중인 아이디입니다."){};
        }
    }
    public void checkPasswordEqual(SignupRequestDto requestDto) {
        if (!requestDto.getPassword().equals(requestDto.getCheck_password())) {
            throw new ConflictException("비밀번호가 일치하지 않습니다."){};
        }
    }

    public User getLoginUserById(String id) {
        if(id == null) return null;

        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}
