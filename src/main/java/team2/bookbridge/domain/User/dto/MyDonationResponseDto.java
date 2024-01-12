package team2.bookbridge.domain.User.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.bookbridge.domain.enums.Curriculum;
import team2.bookbridge.domain.enums.DonationStatus;
import team2.bookbridge.domain.enums.Subject;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyDonationResponseDto {
    private Long book_id;
    private String title;
    private Curriculum curriculum;
    private Subject subject;
    private LocalDateTime created_at;
    private DonationStatus status;
}
