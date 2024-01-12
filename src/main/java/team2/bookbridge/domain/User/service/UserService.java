package team2.bookbridge.domain.User.service;
//
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team2.bookbridge.domain.Book.repository.BookRepository;
import team2.bookbridge.domain.Donation.domain.Donation;
import team2.bookbridge.domain.Donation.repository.DonationRepository;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.User.dto.*;
import team2.bookbridge.domain.User.repositiry.UserRepository;
import team2.bookbridge.domain.enums.Role;
import team2.bookbridge.global.common.exception.ConflictException;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final BookRepository bookRepository;

//     private final BCryptPasswordEncoder encoder;


    @Transactional
    //회원가입
    public SignupResponseDto signup(SignupRequestDto requestDto, Role role) {
        checkIdDuplicate(requestDto.getLogin_id());
        checkPasswordEqual(requestDto);

        User user = userRepository.save(requestDto.toEntity(requestDto.getPassword(), role));

        return SignupResponseDto.builder()
                .user_id(user.getId())
                .loginId(user.getLoginId())
                .build();

    }

    @Transactional
    //로그인
    public LoginResponseDto login(LoginRequestDto requestDto){
        checkIdExist(requestDto.getLogin_id());
        User user = userRepository.findByLoginId(requestDto.getLogin_id());
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
                .user_id(user.getId())
                .login_id(user.getLoginId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Transactional
    public MyPageResponseDto mypage(Long user_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new ConflictException("존재하지 않는 사용자입니다. "){});

        Long total_books = donationRepository.countByAndBenefactorAndDeletedFalse(user);
        return MyPageResponseDto.builder()
                .user_id(user_id)
                .name(user.getName())
                .email(user.getEmail())
                .total_books(total_books)
                .build();
    }

    @Transactional
    public List<MyDonationResponseDto> myDonation(Long user_id){
        User user = userRepository.findById(user_id)
                .orElseThrow(()-> new ConflictException("존재하지 않는 사용자입니다. "){});

        List<Donation> donationList = donationRepository.findByBenefactorIdOrderByIdDesc(user_id);

        return donationList.stream()
                .map(donation -> MyDonationResponseDto.builder()
                        .book_id(donation.getBook().getId())
                        .title(donation.getBook().getTitle())
                        .curriculum(donation.getBook().getCurriculum())
                        .subject(donation.getBook().getSubject())
                        .created_at(donation.getCreatedAt())
                        .status(donation.getStatus())
                        .build())
                .collect(Collectors.toList());
    }


    public void checkIdDuplicate(String login_id) {
        if (userRepository.existsByLoginId(login_id)) {
            throw new ConflictException("이미 사용중인 아이디입니다."){};
        }
    }
    public void checkPasswordEqual(SignupRequestDto requestDto) {
        if (!requestDto.getPassword().equals(requestDto.getCheck_password())) {
            throw new ConflictException("비밀번호가 일치하지 않습니다."){};
        }
    }


    public void checkIdExist(String login_id) {
        if (!userRepository.existsByLoginId(login_id)) {
            throw new ConflictException("존재하지 않는 아이디입니다. "){};
        }
    }

    public void checkUserIdExist(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ConflictException("존재하지 않는 사용자입니다. "){};
        }
    }

}
