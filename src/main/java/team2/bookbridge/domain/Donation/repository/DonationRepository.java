package team2.bookbridge.domain.Donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.bookbridge.domain.Donation.domain.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
}
