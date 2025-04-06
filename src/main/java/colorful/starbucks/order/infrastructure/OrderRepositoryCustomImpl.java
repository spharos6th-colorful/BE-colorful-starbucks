package colorful.starbucks.order.infrastructure;

import colorful.starbucks.order.domain.QOrder;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.response.OrderListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<OrderListResponseDto> getOrderList(OrderListFilterDto orderListFilterDto) {

        QOrder order = QOrder.order;

        return queryFactory
                .select(Projections.constructor(OrderListResponseDto.class,
                        order.createdAt,
                        order.orderCode,
                        order.totalAmount,
                        order.discountAmount
                ))
                .from(order)
                .where(
                        orderListFilterDto.getCursor() != null
                                ? order.orderCode.lt(orderListFilterDto.getCursor())
                                : null
                )
                .orderBy(order.orderCode.desc())
                .limit(orderListFilterDto.getSize() != null ? orderListFilterDto.getSize() : 10)
                .fetch();
    }

}
