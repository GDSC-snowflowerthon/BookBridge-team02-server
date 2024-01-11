package team2.bookbridge.domain.Book.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import team2.bookbridge.domain.Book.domain.Book;
import team2.bookbridge.domain.enums.Curriculum;
import team2.bookbridge.domain.enums.Subject;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static team2.bookbridge.domain.Book.domain.QBook.book;

@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<Book> getSliceOfBook(Long id, int size, String curriculum, String subject, String title) {
       return queryFactory.selectFrom(book)
               .where(ltBookId(id), eqCurriculum(curriculum), eqSubject(subject), containTitle(title))
               .orderBy(book.id.desc())
               .limit(size)
               .fetch();
    }

    private BooleanExpression eqSubject(String subject) {
        return hasText(subject) ? book.subject.eq(Subject.valueOf(subject)) : null;
    }

    private BooleanExpression eqCurriculum(String curriculum) {
        return hasText(curriculum) ? book.curriculum.eq(Curriculum.valueOf(curriculum)) : null;
    }

    private BooleanExpression containTitle(String title) {
        return hasText(title) ? book.title.contains(title) : null;
    }

    private BooleanExpression ltBookId(Long id) {
        return id == null ? null : book.id.lt(id);
    }
}