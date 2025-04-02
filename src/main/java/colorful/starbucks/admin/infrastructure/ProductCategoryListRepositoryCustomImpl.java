package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.dto.response.ProductCategoryCursorResponseDto;
import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.common.util.CursorPage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.admin.domain.QProductCategoryList.productCategoryList;

@Repository
@RequiredArgsConstructor
public class ProductCategoryListRepositoryCustomImpl implements ProductCategoryListRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    private static final Integer DEFAULT_PAGE_SIZE = 20;

    @Override
    public CursorPage<ProductCategoryCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto filter,
                                                                               Long id,
                                                                               int price) {

        JPAQuery<ProductCategoryCursorResponseDto> query = queryFactory.select(
                        Projections.constructor(ProductCategoryCursorResponseDto.class,
                                productCategoryList.id,
                                productCategoryList.productCode
                        )
                )
                .from(productCategoryList)
                .where(
                        minPriceGoe(filter.getMinPrice()),
                        maxPriceLoe(filter.getMaxPrice()),
                        topCategoryEq(filter.getTopCategoryId()),
                        bottomCategoryEq(filter.getBottomCategoryIds()),
                        productCategoryList.isDeleted.isFalse()
                );

        applySorting(query, filter, id, price);
        int pageSize = filter.getSize() == null ? DEFAULT_PAGE_SIZE : filter.getSize();

        List<ProductCategoryCursorResponseDto> content = query.limit(pageSize + 1).fetch();

        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<ProductCategoryCursorResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    private BooleanExpression minPriceGoe(Integer minPrice) {
        return minPrice != null ? productCategoryList.price.goe(minPrice) : null;
    }

    private BooleanExpression maxPriceLoe(Integer maxPrice) {
        return maxPrice != null ? productCategoryList.price.loe(maxPrice) : null;
    }

    private BooleanExpression topCategoryEq(Long topCategoryId) {
        return topCategoryId != null ? productCategoryList.topCategoryId.eq(topCategoryId) : null;
    }

    private BooleanExpression bottomCategoryEq(List<Long> bottomCategoryIds) {
        return bottomCategoryIds != null ? productCategoryList.bottomCategoryId.in(bottomCategoryIds) : null;
    }

    private void applySorting(JPAQuery<ProductCategoryCursorResponseDto> query,
                              ProductCategoryListFilterDto filter,
                              Long productListId,
                              int price) {

        BooleanBuilder builder = new BooleanBuilder();
        if (filter.getSortBy() != null) {
            switch (filter.getSortBy()) {
                case "price,asc":
                    builder.and(productCategoryList.price.gt(price))
                            .or(productCategoryList.price.eq(price).and(productCategoryList.id.loe(productListId)));
                    query.orderBy(productCategoryList.price.asc(), productCategoryList.id.desc());
                    break;
                case "price,desc":
                    builder.and(productCategoryList.price.lt(price))
                            .or(productCategoryList.price.eq(price).and(productCategoryList.id.loe(productListId)));
                    query.orderBy(productCategoryList.price.desc(), productCategoryList.id.desc());
                    break;
                case "createdAt,asc":
                    builder.and(productCategoryList.id.goe(productListId));
                    query.orderBy(productCategoryList.id.asc());
                    break;
                case "createdAt,desc":
                    builder.and(productCategoryList.id.loe(productListId));
                    query.orderBy(productCategoryList.id.desc());
                    break;
                default:
                    builder.and(productCategoryList.id.loe(productListId));
                    query.orderBy(productCategoryList.id.desc());
                    break;
            }
        } else {
            builder.and(productCategoryList.id.loe(productListId));
            query.orderBy(productCategoryList.id.desc());
        }

        query.where(builder);
    }
}