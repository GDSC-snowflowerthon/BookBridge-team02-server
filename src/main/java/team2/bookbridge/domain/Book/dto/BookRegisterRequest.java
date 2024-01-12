package team2.bookbridge.domain.Book.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.Donation.domain.Donation;
import team2.bookbridge.domain.enums.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookRegisterRequest {
    @NotNull(message = "benefactorId is required")
    private Long benefactorId;
    @NotEmpty(message = "title is required")
    private String title;
    @NotEmpty(message = "subject is required")
    private String subject;
    @NotEmpty(message = "curriculum is required")
    private String curriculum;
    @NotEmpty(message = "publisher is required")
    private String publisher;
    @NotNull(message = "writingToolList is required")
    private List<String> writingToolList;
    @NotEmpty(message = "writingDegree is required")
    private String writingDegree;
    @NotNull(message = "preservationStatusList is required")
    private List<String> preservationStatusList;

    public Book toEntity(Donation donation) {
        return Book.builder()
                .title(title)
                .subject(Subject.valueOf(subject))
                .curriculum(Curriculum.valueOf(curriculum))
                .publisher(publisher)
                .writingTool(writingToolList.stream().map(WritingTool::valueOf).collect(Collectors.toList()))
                .writingDegree(WritingDegree.valueOf(writingDegree))
                .preservationStatus(preservationStatusList.stream().map(PreservationStatus::valueOf).collect(Collectors.toList()))
                .donation(donation)
                .build();
    }
}
