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

    private static final Integer DEFAULT_PAGE_SIZE = 10;
    private final JPAQueryFactory queryFactory;

    @Override
    public CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto) {
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
                       orderCodeLessThanOrEqual(orderListFilterDto.getCursor()),
                       order.isDeleted.isFalse()
                 );
        applySorting(query, orderListFilterDto);

        int pageSize = orderListFilterDto.getSize() != null ? orderListFilterDto.getSize() : DEFAULT_PAGE_SIZE;

        List<OrderCursorResponseDto> content = query
                .limit(pageSize + 1)
                .fetch();

        Long nextCursor = null;
        boolean hasNext = false;

        if(content.size() > pageSize) {
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

    private BooleanExpression orderCodeLessThanOrEqual(Long orderCode) {
        return orderCode != null ? order.orderCode.loe(orderCode) : null;
    }

    private void applySorting(JPAQuery<OrderCursorResponseDto> query,
                              OrderListFilterDto orderListFilterDto) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Long cursor = orderListFilterDto.getCursor();

        if (orderListFilterDto.getSortBy() != null) {
            switch (orderListFilterDto.getSortBy()) {
                case "createdAt,asc":
                    if (cursor != null) booleanBuilder.and(order.orderCode.goe(cursor));
                    query.orderBy(order.createdAt.asc());
                    break;
                case "createdAt,desc":
                    if (cursor != null) booleanBuilder.and(order.orderCode.loe(cursor));
                    query.orderBy(order.createdAt.desc());
                    break;
                default:
                    if (cursor != null) booleanBuilder.and(order.orderCode.loe(cursor));
                    query.orderBy(order.createdAt.desc());
                    break;
            }
        } else {
            if (cursor != null) booleanBuilder.and(order.orderCode.loe(cursor));
            query.orderBy(order.createdAt.desc());
        }

        query.where(booleanBuilder);
    }

}