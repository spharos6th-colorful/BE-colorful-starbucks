package colorful.starbucks.order.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.response.OrderDetailCursorResponseDto;
import com.querydsl.core.BooleanBuilder;
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
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private final JPAQueryFactory queryFactory;

    @Override
    public CursorPage<OrderDetail> getOrderDetailList(OrderDetailFilterDto orderDetailFilterDto) {

        int pageSize = orderDetailFilterDto.getSize() != null ? orderDetailFilterDto.getSize() : DEFAULT_PAGE_SIZE;
        int offset = 0;
        BooleanBuilder builder = new BooleanBuilder();

        Long cursor = orderDetailFilterDto.getCursor();
        if (cursor != null) {
            builder.and(orderDetail.id.loe(cursor));
        } else {
            int currentPage = orderDetailFilterDto.getPage() != null ? orderDetailFilterDto.getPage() : DEFAULT_PAGE_NUMBER;
            offset = currentPage == 0 ? 0 : (currentPage) * pageSize;
        }

        List<OrderDetail> content = queryFactory.selectFrom(orderDetail)
                .where(
                        builder

                )
                .offset(offset)
                .limit(pageSize + 1)
                .orderBy(orderDetail.id.desc())
                .fetch();


        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<OrderDetail>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }
}

