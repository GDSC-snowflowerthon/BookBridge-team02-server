package team2.bookbridge.domain.Book.dto;

import lombok.Builder;
import lombok.Getter;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.enums.WritingTool;
import team2.bookbridge.domain.enums.PreservationStatus;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BookResponse {
    private final Long benefactorId;
    private final Long bookId;
    private final String outsideImageUrl;
    private final String insideImageUrl;
    private final String title;
    private final String subject;
    private final String curriculum;
    private final String publisher;
    private final List<String> writingToolList;
    private final String writingDegree;
    private final List<String> preservationStatusList;

    @Builder
    public BookResponse(Long benefactorId, Long bookId, String outsideImageUrl, String insideImageUrl, String title,
                        String subject, String curriculum, String publisher, List<String> writingToolList,
                        String writingDegree, List<String> preservationStatusList) {
        this.benefactorId = benefactorId;
        this.bookId = bookId;
        this.outsideImageUrl = outsideImageUrl;
        this.insideImageUrl = insideImageUrl;
        this.title = title;
        this.subject = subject;
        this.curriculum = curriculum;
        this.publisher = publisher;
        this.writingToolList = writingToolList;
        this.writingDegree = writingDegree;
        this.preservationStatusList = preservationStatusList;
    }

    public static BookResponse from(Book book) {
        return BookResponse.builder()
                .benefactorId(book.getDonation().getBenefactor().getId())
                .bookId(book.getId())
                .outsideImageUrl(book.getOutsideImageUrl())
                .insideImageUrl(book.getInsideImageUrl())
                .title(book.getTitle())
                .subject(book.getSubject().name())
                .curriculum(book.getCurriculum().name())
                .publisher(book.getPublisher())
                .writingToolList(book.getWritingTool().stream().map(WritingTool::name).collect(Collectors.toList()))
                .writingDegree(book.getWritingDegree().name())
                .preservationStatusList(book.getPreservationStatus().stream().map(PreservationStatus::name).collect(Collectors.toList()))
                .build();
    }
}
