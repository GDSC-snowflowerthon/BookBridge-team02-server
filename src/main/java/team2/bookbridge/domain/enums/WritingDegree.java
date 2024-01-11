package team2.bookbridge.domain.enums;

import lombok.Getter;

@Getter
public enum WritingDegree {
    VERY_GOOD("매우 깨끗함"),
    GOOD("깨끗함"),
    MEDIUM("보통"),
    BAD("필기 있음"),
    VERY_BAD("필기량 많음");

    private final String writingDegree;

    WritingDegree(String writingDegree) {
        this.writingDegree = writingDegree;
    }
}
