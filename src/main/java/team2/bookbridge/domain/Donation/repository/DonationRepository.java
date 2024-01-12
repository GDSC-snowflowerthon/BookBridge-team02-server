package team2.bookbridge.domain.Donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.bookbridge.domain.Donation.domain.Donation;
import team2.bookbridge.domain.User.domain.User;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    Long countByAndBenefactorAndDeletedFalse(User user);

    Donation findByBookId(Long book_id);
    List<Donation> findByBenefactorIdOrderByIdDesc(Long userId);

}
