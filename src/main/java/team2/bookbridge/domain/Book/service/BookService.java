package team2.bookbridge.domain.Book.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.Book.dto.*;
import team2.bookbridge.domain.Book.exception.BookNotFoundException;
import team2.bookbridge.domain.Book.handler.S3Uploader;
import team2.bookbridge.domain.Book.repository.BookRepository;
import team2.bookbridge.domain.Donation.service.DonationService;
import team2.bookbridge.domain.User.domain.User;
import team2.bookbridge.domain.User.repositiry.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final S3Uploader s3Uploader;
    private final DonationService donationService;
    private final UserRepository userRepository;

    @Transactional
    public BookRegisterResponse register(MultipartFile outsideImage, MultipartFile insideImage, BookRegisterRequest request) throws IOException {
        String outsideImageUrl = s3Uploader.upload(outsideImage, "images");
        String insideImageUrl = s3Uploader.upload(insideImage, "images");

        User user = userRepository.findById(request.getBenefactorId()).orElseThrow(() -> new IllegalArgumentException("User를 찾을 수 없습니다."));
        userRepository.save(user);

        Book book = request.toEntity(donationService.createDonation(user));
        book.addOutsideImageUrl(outsideImageUrl);
        book.addInsideImageUrl(insideImageUrl);
        book.getDonation().addBook(book);
        bookRepository.save(book);

        return BookRegisterResponse.builder()
                .id(book.getId())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt())
                .build();
    }

    @Transactional
    public BookResponse findBookById(Long id) {
        return BookResponse.from(bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }

    @Transactional
    public BookListResponse findAllBooks(Long id, List<String> curriculum, List<String> subject, String title, String publisher) {
        return new BookListResponse(bookRepository.getSliceOfBook(id, 5, curriculum, subject, title, publisher)
                .stream().map(BookSimpleResponse::from).collect(Collectors.toList()));
    }

    @Transactional
    public BookResponse updateBook(Long id, MultipartFile outsideImage, MultipartFile insideImage, BookUpdateRequest request) throws IOException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        updateImage(outsideImage, book::getOutsideImageUrl, book::addOutsideImageUrl);
        updateImage(insideImage, book::getInsideImageUrl, book::addInsideImageUrl);

        book.update(request);

        return BookResponse.from(book);
    }

    private void updateImage(MultipartFile image, Supplier<String> imageUrlSupplier, Consumer<String> imageUrlConsumer) throws IOException {
        if (!ObjectUtils.isEmpty(image)) {
            String imageUrl = imageUrlSupplier.get();
            String fileName = imageUrl.substring(imageUrl.indexOf("images/"));
            s3Uploader.delete(fileName);
            imageUrl = s3Uploader.upload(image, "images");
            imageUrlConsumer.accept(imageUrl);
        }
    }

    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.getDonation().delete(true);
        bookRepository.delete(book);
    }
}
