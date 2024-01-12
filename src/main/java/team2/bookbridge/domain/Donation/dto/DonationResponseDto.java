package team2.bookbridge.domain.Donation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.bookbridge.domain.enums.DonationStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationResponseDto {
    private Long book_id;
    private DonationStatus status;
}
