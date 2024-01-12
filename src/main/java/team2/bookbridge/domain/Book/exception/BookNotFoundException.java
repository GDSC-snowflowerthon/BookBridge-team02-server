package team2.bookbridge.domain.Book.exception;

import team2.bookbridge.global.common.exception.NotFoundException;

public class BookNotFoundException extends NotFoundException {
    public BookNotFoundException(Long id) {
        super("존재하지 않는 참고서입니다. (id: " + id + ")");
    }
}
