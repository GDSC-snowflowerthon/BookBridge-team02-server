package team2.bookbridge.domain.Book.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team2.bookbridge.domain.Book.dto.*;
import team2.bookbridge.domain.Book.service.BookService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    BookRegisterResponse register(
            @RequestPart(value = "outsideImage") MultipartFile outsideImage,
            @RequestPart(value = "insideImage") MultipartFile insideImage,
            @Valid @RequestPart(value = "request") BookRegisterRequest request) throws IOException {
        return bookService.register(outsideImage, insideImage, request);
    }

    @GetMapping("/{id}")
    BookResponse findBookById(@PathVariable("id") Long id) {
        return bookService.findBookById(id);
    }

    @GetMapping
    BookListResponse findAllBooks(
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "curriculum", required = false) List<String> curriculum,
            @RequestParam(name = "subject", required = false) List<String> subject,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "publisher", required = false) String publisher
    ) {
        return bookService.findAllBooks(id, curriculum, subject, title, publisher);
    }

    @PatchMapping("/{id}")
    BookResponse update(
            @PathVariable("id") Long id,
            @RequestPart(value = "outsideImage", required = false) MultipartFile outsideImage,
            @RequestPart(value = "insideImage", required = false) MultipartFile insideImage,
            @Valid @RequestPart(value = "request", required = false) BookUpdateRequest request
    ) throws IOException {
       return bookService.updateBook(id, outsideImage, insideImage, request);
    }

    @DeleteMapping("/{id}")
    void delete(
            @PathVariable("id") Long id
    ) {
        bookService.delete(id);
    }
}
