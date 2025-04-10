package colorful.starbucks.event.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.coupon.domain.MemberCoupon;
import colorful.starbucks.event.domain.EventProduct;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.event.domain.QEventProduct.eventProduct;

@Repository
@RequiredArgsConstructor
public class EventProductRepositoryCustomImpl implements EventProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public CursorPage<Long> getEventProductCodesByEventUuid(EventProductCodesRequestDto eventProductCodesRequestDto) {

        int pageSize = eventProductCodesRequestDto.getSize() == null ? DEFAULT_PAGE_SIZE : eventProductCodesRequestDto.getSize();
        int offset = 0;

        BooleanBuilder builder = new BooleanBuilder();
        if (eventProductCodesRequestDto.getCursor() == null) {
            int currentPage = eventProductCodesRequestDto.getPage() == null ? DEFAULT_PAGE_NUMBER : eventProductCodesRequestDto.getPage();
            offset = currentPage == 0 ? 0 : (currentPage) * pageSize;
        } else {
            builder.and(eventProduct.id.loe(eventProductCodesRequestDto.getCursor()));
        }
        List<EventProduct> content = queryFactory
                .selectFrom(eventProduct)
                .where(
                        eventProduct.eventUuid.eq(eventProductCodesRequestDto.getEventUuid()),
                        builder
                )
                .offset(offset)
                .limit(pageSize + 1)
                .orderBy(eventProduct.id.desc())
                .fetch();

        boolean hasNext = false;
        Long nextCursor = null;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            hasNext = true;
            content.remove(pageSize);
        }

        return CursorPage.<Long>builder()
                .content(
                        content.stream()
                                .map(EventProduct::getProductCode)
                                .toList()
                )
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();

    }
}
