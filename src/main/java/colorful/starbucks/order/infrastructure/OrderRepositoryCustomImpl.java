package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.dto.OrderListFilterDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static colorful.starbucks.order.domain.QOrder.order;


@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;


    @Override
    public CursorPage<Order> getOrderList(OrderListFilterDto orderListFilterDto) {

        int pageSize = orderListFilterDto.getSize() != null ? orderListFilterDto.getSize() : DEFAULT_PAGE_SIZE;
        int offset = 0;
        BooleanBuilder builder = new BooleanBuilder();

        Long cursor = orderListFilterDto.getCursor();
        if (cursor != null) {
            builder.and(order.id.loe(cursor));
        } else {
            int currentPage = orderListFilterDto.getPage() != null ? orderListFilterDto.getPage() : DEFAULT_PAGE_NUMBER;
            offset = currentPage == 0 ? 0 : (currentPage) * pageSize;
        }

        List<Order> content = queryFactory.selectFrom(order)
                .where(createdAtGreaterThanOrEqual(orderListFilterDto.getStartDate()),
                        createdAtLessThanOrEqual(orderListFilterDto.getEndDate()),
                        builder
                )
                .offset(offset)
                .limit(pageSize + 1)
                .orderBy(order.id.desc())
                .fetch();


        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<Order>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    private BooleanExpression createdAtGreaterThanOrEqual(LocalDateTime startDateTime) {
        return startDateTime != null ? order.createdAt.goe(startDateTime) : null;
    }

    private BooleanExpression createdAtLessThanOrEqual(LocalDateTime endDateTime) {
        return endDateTime != null ? order.createdAt.loe(endDateTime) : null;
    }
}
