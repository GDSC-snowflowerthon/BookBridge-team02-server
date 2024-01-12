package team2.bookbridge.domain.Book.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class BookListResponse {
    private final List<BookSimpleResponse> bookList;

    public BookListResponse(List<BookSimpleResponse> bookList) {
        this.bookList = bookList;
    }
}
