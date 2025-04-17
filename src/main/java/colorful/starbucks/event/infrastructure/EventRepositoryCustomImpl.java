package colorful.starbucks.event.infrastructure;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.domain.EventStatus;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.event.domain.QEvent.event;

@Repository
@RequiredArgsConstructor
public class EventRepositoryCustomImpl implements EventRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public Page<Event> getEvents(EventFilterRequestDto eventFilterRequestDto) {

        int currentPage = eventFilterRequestDto.getPage() != null ? eventFilterRequestDto.getPage() : DEFAULT_PAGE_NUMBER;
        int size = eventFilterRequestDto.getSize() != null ? eventFilterRequestDto.getSize() : DEFAULT_PAGE_SIZE;
        int offset = currentPage == 0 ? 0 : (currentPage) * size;

        List<Event> content = queryFactory.selectFrom(event)
                .where(
                        eqEventStatus(eventFilterRequestDto.getStatus()),
                        event.isDeleted.eq(false)
                )
                .offset(offset)
                .limit(size)
                .orderBy(event.id.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(event.count())
                .from(event)
                .where(
                        eqEventStatus(eventFilterRequestDto.getStatus()),
                        event.isDeleted.eq(false)
                );

        return PageableExecutionUtils.getPage(
                content,
                PageRequest.of(currentPage, size),
                countQuery::fetchOne
        );
    }

    private BooleanExpression eqEventStatus(EventStatus eventStatus) {
        return eventStatus != null ? event.status.eq(eventStatus) : null;
    }
}
