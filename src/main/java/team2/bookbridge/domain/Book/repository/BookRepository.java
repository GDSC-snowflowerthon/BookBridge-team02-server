package team2.bookbridge.domain.Book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team2.bookbridge.domain.Book.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {
}
