package team2.bookbridge.domain.enums;

import lombok.Getter;

@Getter
public enum DonationStatus {
    WAITING("대기중"),
    ACCEPTANCE("수락됨");

    private final String donationStatus;

    DonationStatus(String donationStatus) {
        this.donationStatus = donationStatus;
    }
}
