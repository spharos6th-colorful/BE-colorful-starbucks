package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.response.OrderDetailCursorResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.order.domain.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class OrderDetailRepositoryCustomImpl implements OrderDetailRepositoryCustom {

    private static final Integer DEFAULT_PAGE_SIZE = 20;
    private final JPAQueryFactory queryFactory;

    @Override
    public CursorPage<OrderDetailCursorResponseDto> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto) {
        JPAQuery<OrderDetailCursorResponseDto> query = queryFactory.select(
                        Projections.constructor(OrderDetailCursorResponseDto.class,
                                orderDetail.productCode,
                                orderDetail.productName,
                                orderDetail.size,
                                orderDetail.color,
                                orderDetail.quantity,
                                orderDetail.price,
                                orderDetail.carving,
                                orderDetail.carvingContent,
                                orderDetail.productDetailThumbnailUrl

                        )
                )
                .from(orderDetail)
                .where();
        int pageSize = orderDetailFilterDto.getSize() != null ? orderDetailFilterDto.getSize() : DEFAULT_PAGE_SIZE;

        List<OrderDetailCursorResponseDto> content = query
                .limit(pageSize + 1)
                .fetch();

        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getProductCode();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<OrderDetailCursorResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }
}

