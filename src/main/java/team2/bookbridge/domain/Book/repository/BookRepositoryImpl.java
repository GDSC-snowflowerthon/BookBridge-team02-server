package team2.bookbridge.domain.Book.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.ObjectUtils;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.enums.Curriculum;
import team2.bookbridge.domain.enums.Subject;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static team2.bookbridge.domain.Book.domain.QBook.book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<Book> getSliceOfBook(Long id, int size, List<String> curriculum, List<String> subject, String title, String publisher) {
       return queryFactory.selectFrom(book)
               .where(ltBookId(id), inCurriculums(curriculum), inSubjects(subject), containTitle(title), containPublisher(publisher))
               .orderBy(book.id.desc())
               .limit(size)
               .fetch();
    }

    private BooleanExpression inSubjects(List<String> subject) {
        return !ObjectUtils.isEmpty(subject) ? book.subject.in(subject.stream().map(Subject::valueOf).collect(Collectors.toList())) : null;
    }

    private BooleanExpression inCurriculums(List<String> curriculum) {
        return !ObjectUtils.isEmpty(curriculum) ? book.curriculum.in(curriculum.stream().map(Curriculum::valueOf).collect(Collectors.toList())) : null;
    }

    private BooleanExpression containTitle(String title) {
        return hasText(title) ? book.title.contains(title) : null;
    }

    private BooleanExpression containPublisher(String publisher) {
        return hasText(publisher) ? book.publisher.contains(publisher) : null;
    }

    private BooleanExpression ltBookId(Long id) {
        return id == null ? null : book.id.lt(id);
    }
}