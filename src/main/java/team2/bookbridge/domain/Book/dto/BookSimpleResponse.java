package team2.bookbridge.domain.Book.dto;

import lombok.Builder;
import lombok.Getter;
import team2.bookbridge.domain.Book.domain.Book;

@Getter
public class BookSimpleResponse {
    private final Long bookId;
    private final String outsideImageUrl;
    private final String title;
    private final String subject;
    private final String curriculum;
    private final String publisher;

    @Builder
    public BookSimpleResponse(Long bookId, String outsideImageUrl,
                              String title, String subject, String curriculum, String publisher) {
        this.bookId = bookId;
        this.outsideImageUrl = outsideImageUrl;
        this.title = title;
        this.subject = subject;
        this.curriculum = curriculum;
        this.publisher = publisher;
    }

    public static BookSimpleResponse from(Book book) {
        return BookSimpleResponse.builder()
                .bookId(book.getId())
                .outsideImageUrl(book.getOutsideImageUrl())
                .title(book.getTitle())
                .subject(book.getSubject().name())
                .curriculum(book.getCurriculum().name())
                .publisher(book.getPublisher())
                .build();
    }
}
