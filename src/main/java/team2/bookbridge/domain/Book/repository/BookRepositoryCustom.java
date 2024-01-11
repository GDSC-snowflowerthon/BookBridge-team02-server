package team2.bookbridge.domain.Book.repository;

import team2.bookbridge.domain.Book.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> getSliceOfBook(Long id, int size, String curriculum, String subject, String title);
}
