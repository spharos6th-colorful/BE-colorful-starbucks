package colorful.starbucks.event.infrastructure;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.domain.EventStatus;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.event.domain.QEvent.event;

@Repository
@RequiredArgsConstructor
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Event> getEvents(Pageable pageable) {
        List<Event> content = queryFactory.selectFrom(event)
                .where(
                        event.status.eq(EventStatus.ONGOING),
                        event.isDeleted.isFalse()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(event.id.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(event.count())
                .from(event)
                .where(
                        event.status.eq(EventStatus.ONGOING),
                        event.isDeleted.isFalse()
                );

        return PageableExecutionUtils.getPage(
                content,
                pageable,
                countQuery::fetchOne
        );
    }
}
