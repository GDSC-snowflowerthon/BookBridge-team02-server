package team2.bookbridge.domain.Book.domain;

import lombok.Builder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.ObjectUtils;
import team2.bookbridge.domain.Book.dto.BookUpdateRequest;
import team2.bookbridge.domain.Donation.domain.Donation;
import team2.bookbridge.domain.enums.*;
import team2.bookbridge.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "books")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE book_id = ?")
@Where(clause = "deleted = false")
@Getter
@NoArgsConstructor
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "subject", nullable = false)
    private Subject subject;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "curriculum", nullable = false)
    private Curriculum curriculum;

    @Column(name = "publisher", nullable = false)
    private String publisher;

    @Column(name = "outside_image_url", nullable = false)
    private String outsideImageUrl;

    @Column(name = "inside_image_url", nullable = false)
    private String insideImageUrl;

    @ElementCollection
    @CollectionTable(name = "books_writing_tool", joinColumns = @JoinColumn(name = "books_id", referencedColumnName = "book_id"))
    @Enumerated(value = EnumType.STRING)
    @Column(name = "writing_tool", nullable = false)
    private List<WritingTool> writingTool;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "writing_degree", nullable = false)
    private WritingDegree writingDegree;

    @ElementCollection
    @CollectionTable(name = "books_preservation_status", joinColumns = @JoinColumn(name = "books_id", referencedColumnName = "book_id"))
    @Enumerated(value = EnumType.STRING)
    @Column(name = "preservation_status", nullable = false)
    private List<PreservationStatus> preservationStatus;

    @OneToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Builder
    public Book(String title, Subject subject, Curriculum curriculum, String publisher, List<WritingTool> writingTool,
                WritingDegree writingDegree, List<PreservationStatus> preservationStatus, Donation donation) {
        this.title = title;
        this.subject = subject;
        this.curriculum = curriculum;
        this.publisher = publisher;
        this.writingTool = writingTool;
        this.writingDegree = writingDegree;
        this.preservationStatus = preservationStatus;
        this.donation = donation;
    }

    public void addOutsideImageUrl(String outsideImageUrl) {
        this.outsideImageUrl = outsideImageUrl;
    }

    public void addInsideImageUrl(String insideImageUrl) {
        this.insideImageUrl = insideImageUrl;
    }

    public void update(BookUpdateRequest request) {
        if (!ObjectUtils.isEmpty(request)) {
            if (!ObjectUtils.isEmpty(request.getTitle())) {
                this.title = request.getTitle();
            }

            if (!ObjectUtils.isEmpty(request.getSubject())) {
                this.subject = Subject.valueOf(request.getSubject());
            }

            if (!ObjectUtils.isEmpty(request.getCurriculum())) {
                this.curriculum = Curriculum.valueOf(request.getCurriculum());
            }

            if (!ObjectUtils.isEmpty(request.getWritingToolList())) {
                this.writingTool = request.getWritingToolList().stream()
                        .map(WritingTool::valueOf).collect(Collectors.toList());
            }

            if (!ObjectUtils.isEmpty(request.getWritingDegree())) {
                this.writingDegree = WritingDegree.valueOf(request.getWritingDegree());
            }

            if (!ObjectUtils.isEmpty(request.getPreservationStatusList())) {
                this.preservationStatus = request.getPreservationStatusList().stream()
                        .map(PreservationStatus::valueOf).collect(Collectors.toList());
            }
        }
    }
}
