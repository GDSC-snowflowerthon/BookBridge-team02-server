package team2.bookbridge.domain.User.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import team2.bookbridge.domain.User.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsById(String id);
    Optional<User> findById(String id);
}
