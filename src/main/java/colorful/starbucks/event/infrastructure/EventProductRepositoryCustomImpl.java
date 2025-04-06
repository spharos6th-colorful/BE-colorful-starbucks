package colorful.starbucks.event.infrastructure;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<Long> getEventProducts(String eventUuid, Integer page, Integer size) {

        int currentPage = page != null ? page : DEFAULT_PAGE_NUMBER;
        int pageSize = size != null ? size : DEFAULT_PAGE_SIZE;
        int offset = currentPage == 0 ? 0 : (currentPage) * pageSize;

        List<Long> content = queryFactory.select(eventProduct.productCode)
                .from(eventProduct)
                .where(eventProduct.eventUuid.eq(eventUuid))
                .offset(offset)
                .limit(pageSize)
                .orderBy(eventProduct.id.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory.select(eventProduct.count())
                .from(eventProduct)
                .where(eventProduct.eventUuid.eq(eventUuid));

        return PageableExecutionUtils.getPage(content,
                PageRequest.of(currentPage, pageSize),
                countQuery::fetchOne
        );
    }
}
