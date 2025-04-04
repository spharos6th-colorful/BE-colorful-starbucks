package colorful.starbucks.product.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static colorful.starbucks.admin.domain.QProductCategoryList.productCategoryList;
import static colorful.starbucks.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public CursorPage<ProductResponseDto> getProductsByFilter(ProductFilterDto filter,
                                            Long productId,
                                            int price,
                                            Pageable pageable) {

        JPAQuery<ProductResponseDto> query = queryFactory.select(
                        Projections.constructor(ProductResponseDto.class,
                                product.productName,
                                product.productCode,
                                product.description,
                                product.price,
                                product.productImageUrl,
                                product.productThumbnailUrl,
                                product.markable
                        )
                )
                .from(product)
//                .leftJoin(productCategoryList)
//                .on(productCategoryList.productCode.eq(product.productCode))
                .where(
                        minPriceGoe(filter.getMinPrice()),
                        maxPriceLoe(filter.getMaxPrice()),
                        topCategoryEq(filter.getTopCategory()),
                        bottomCategoryEq(filter.getBottomCategory()),
                        product.isDeleted.isFalse()
                );

        applySorting(query, filter, productId, price);

        List<ProductResponseDto> content = query.limit(pageable.getPageSize() + 1).fetch();

        ProductResponseDto cursorProduct = content.get(content.size() - 1);
        boolean hasNext = hasNextPage(content, pageable);
        Long nextCursor = hasNext ? cursorProduct.getProductCode() : null;

        return CursorPage.<ProductResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    private boolean hasNextPage(List<ProductResponseDto> content, Pageable pageable) {
        boolean hasNext = content.size() > pageable.getPageSize();
        if (hasNext) {
            content.remove(pageable.getPageSize());
        }
        return hasNext;
    }

    private BooleanExpression minPriceGoe(Integer minPrice) {
        return minPrice != null ? product.price.goe(minPrice) : null;
    }

    private BooleanExpression maxPriceLoe(Integer maxPrice) {
        return maxPrice != null ? product.price.loe(maxPrice) : null;
    }

    private BooleanExpression topCategoryEq(String topCategory) {
        return StringUtils.hasText(topCategory) ? productCategoryList.topCategoryName.eq(topCategory) : null;
    }

    private BooleanExpression bottomCategoryEq(String bottomCategory) {
        return StringUtils.hasText(bottomCategory) ? productCategoryList.bottomCategoryName.eq(bottomCategory) : null;
    }

    private void applySorting(JPAQuery<ProductResponseDto> query, ProductFilterDto filter, Long productId, int price) {

        BooleanBuilder builder = new BooleanBuilder();
        if (filter.getSortBy() != null) {
            switch (filter.getSortBy()) {
                case "price,asc":
                    builder.and(product.price.gt(price))
                            .or(product.price.eq(price).and(product.id.lt(productId)));
                    query.orderBy(product.price.asc(), product.id.desc());
                    break;
                case "price,desc":
                    builder.and(product.price.lt(price))
                            .or(product.price.eq(price).and(product.id.lt(productId)));
                    query.orderBy(product.price.desc(), product.id.desc());
                    break;
                case "createdAt,asc":
                    builder.and(product.id.gt(productId));
                    query.orderBy(product.id.asc());
                    break;
                case "createdAt,desc":
                    builder.and(product.id.lt(productId));
                    query.orderBy(product.id.desc());
                    break;
                default:
                    builder.and(product.id.lt(productId));
                    query.orderBy(product.id.desc());
                    break;
            }
        } else {
            builder.and(product.id.lt(productId));
            query.orderBy(product.id.desc());
        }

        query.where(builder);
    }
}
