package team2.bookbridge.domain.enums;

import lombok.Getter;

@Getter
public enum Curriculum {
    ELEMENTARY("초등"),
    SECONDARY("중등"),
    HIGHER("고등");

    private final String curriculum;

    Curriculum(String curriculum) {
        this.curriculum = curriculum;
    }
}
