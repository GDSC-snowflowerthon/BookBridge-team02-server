package team2.bookbridge.domain.Donation.domain;

import lombok.Builder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.enums.DonationStatus;
import team2.bookbridge.global.common.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public void delete(boolean deleted) {
        this.deleted = deleted;
    }
}
