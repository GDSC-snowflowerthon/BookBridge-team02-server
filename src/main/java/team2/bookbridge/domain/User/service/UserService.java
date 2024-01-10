package team2.bookbridge.domain.User.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.bookbridge.domain.User.dto.UserSigninRequestDto;
import team2.bookbridge.domain.User.repositiry.UserRepository;
import team2.bookbridge.domain.enums.Role;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

     private final BCryptPasswordEncoder encoder;

    public boolean checkIdDuplicate(String id) {
        return userRepository.existsById(id);
    }

    //회원가입
    public void signup(UserSigninRequestDto requestDto, Role role) {
        userRepository.save(requestDto.toEntity(encoder.encode(requestDto.getPassword()), role));
    }


}
