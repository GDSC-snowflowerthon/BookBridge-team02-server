package team2.bookbridge.domain.Book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    private String title;
    private String subject;
    private String curriculum;
    private String publisher;
    private List<String> writingToolList;
    private String writingDegree;
    private List<String> preservationStatusList;
}
