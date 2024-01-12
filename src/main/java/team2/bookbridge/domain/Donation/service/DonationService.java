package team2.bookbridge.domain.Donation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team2.bookbridge.domain.Donation.domain.Donation;
import team2.bookbridge.domain.Donation.dto.DonationRequestDto;
import team2.bookbridge.domain.Donation.dto.DonationResponseDto;
import team2.bookbridge.domain.Donation.repository.DonationRepository;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.enums.DonationStatus;

@Service
@RequiredArgsConstructor
public class DonationService {
    private final DonationRepository donationRepository;

    @Transactional
    public Donation createDonation(User benefactor) {
        return donationRepository.save(
                Donation.builder()
                        .status(DonationStatus.WAITING)
                        .benefactor(benefactor)
                        .build()
        );
    }

    @Transactional
    public DonationResponseDto setStatus(DonationRequestDto requestDto){
        Donation donation = donationRepository.findByBookId(requestDto.getBook_id());
        donation.update(donation);
        Donation updateDonation = donationRepository.save(donation);

        return DonationResponseDto.builder()
                .book_id(updateDonation.getId())
                .status(updateDonation.getStatus())
                .build();
    }
}
