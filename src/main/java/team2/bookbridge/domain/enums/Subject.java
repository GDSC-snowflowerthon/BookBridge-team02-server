package team2.bookbridge.domain.enums;

import lombok.Getter;

@Getter
public enum Subject {
    KOREAN("국어"),
    ENGLISH("영어"),
    MATH("수학"),
    SOCIAL_STUDIES("사회"),
    SCIENCE("과학"),
    HISTORY("한국사"),
    ETC("기타");

    private final String subject;

    Subject(String subject) {
        this.subject = subject;
    }
}