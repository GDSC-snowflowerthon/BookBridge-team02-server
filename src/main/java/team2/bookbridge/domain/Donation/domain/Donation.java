package team2.bookbridge.domain.Donation.domain;

import lombok.Builder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.util.ObjectUtils;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.Book.dto.BookUpdateRequest;
import team2.bookbridge.domain.Donation.dto.DonationRequestDto;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.enums.*;
import team2.bookbridge.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.stream.Collectors;

@Entity
@Table(name = "donations")
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE book_id = ?")
@Where(clause = "deleted = false")
@Getter
@NoArgsConstructor
public class Donation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DonationStatus status;

    // 후원자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "benefactor_id")
    private User benefactor;

    // 수혜자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id")
    private User beneficiary;

    @OneToOne(mappedBy = "donation")
    private Book book;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Builder
    public Donation(DonationStatus status, User benefactor) {
        this.status = status;
        this.benefactor = benefactor;
    }

    public void addBeneficiary(User beneficiary) {
        this.beneficiary = beneficiary;
    }

    public void addBook(Book book) {
        this.book = book;
    }

    public void delete(boolean deleted) {
        this.deleted = deleted;
    }

    public void update(Donation donation) {
        if (!ObjectUtils.isEmpty(donation)) {
            if(donation.status.equals(DonationStatus.WAITING)){
                this.status = DonationStatus.ACCEPTANCE;
            }
            else{
                this.status = DonationStatus.WAITING;
            }
        }
    }
}
