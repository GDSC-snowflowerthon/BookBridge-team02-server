package team2.bookbridge.domain.enums;

import lombok.Getter;

@Getter
public enum PreservationStatus {
    COVER_CLEAN("겉표지 깨끗함"),
    NO_NAME("이름(서명) 없음"),
    NO_DISCOLORATION("페이지 변색 없음"),
    NO_PAGE_DAMAGE("페이지 훼손 없음");

    private final String preservationStatus;

    PreservationStatus(String preservationStatus) {
        this.preservationStatus = preservationStatus;
    }
}
