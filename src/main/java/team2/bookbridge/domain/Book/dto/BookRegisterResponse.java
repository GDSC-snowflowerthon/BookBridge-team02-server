package team2.bookbridge.domain.Book.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookRegisterResponse {
    private final Long id;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public BookRegisterResponse(Long id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
