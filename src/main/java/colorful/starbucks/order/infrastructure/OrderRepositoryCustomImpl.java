package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
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
    public CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto) {

        int pageSize = orderListFilterDto.getSize() != null ? orderListFilterDto.getSize() : DEFAULT_PAGE_SIZE;
        int offset = 0;
        BooleanBuilder builder = new BooleanBuilder();

        Long cursor = orderListFilterDto.getCursor();
        if (cursor != null) {
            builder.and(order.orderCode.loe(cursor));
        } else {
            int currentPage = orderListFilterDto.getPage() != null ? orderListFilterDto.getPage() : DEFAULT_PAGE_NUMBER;
            offset = currentPage == 0 ? 0 : (currentPage) * pageSize;
        }

        JPAQuery<OrderCursorResponseDto> query = queryFactory.select(
                        Projections.constructor(OrderCursorResponseDto.class,
                                order.createdAt,
                                order.orderCode,
                                order.totalAmount,
                                order.discountAmount
                        )
                )
                .from(order)
                .where(createdAtGreaterThanOrEqual(orderListFilterDto.getStartDate()),
                        createdAtLessThanOrEqual(orderListFilterDto.getEndDate()),
                        builder
                )
                .offset(offset)
                .limit(pageSize + 1)
                .orderBy(order.id.desc());


        List<OrderCursorResponseDto> content = query
                .fetch();

        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getOrderCode();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<OrderCursorResponseDto>builder()
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
