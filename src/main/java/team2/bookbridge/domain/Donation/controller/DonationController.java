package team2.bookbridge.domain.Donation.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team2.bookbridge.domain.Donation.dto.DonationRequestDto;
import team2.bookbridge.domain.Donation.dto.DonationResponseDto;
import team2.bookbridge.domain.Donation.service.DonationService;

@RestController
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;

    @PutMapping("/donate")
    public DonationResponseDto setStatus(@RequestBody DonationRequestDto requestDto){
        return donationService.setStatus(requestDto);

    }
}
