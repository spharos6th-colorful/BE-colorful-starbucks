package colorful.starbucks.product.infrastructure;

import colorful.starbucks.admin.dto.ProductIdAndPriceDto;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.product.domain.QProduct.product;
import static colorful.starbucks.product.domain.QProductFilter.productFilter;

@Repository
@RequiredArgsConstructor
public class ProductFilterRepositoryCustomImpl implements ProductFilterRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public CursorPage<ProductCursorResponseDto> getFilteredProductList(ProductFilterDto productFilterDto,
                                                                       Long id,
                                                                       int price) {

        int pageSize = productFilterDto.getSize() != null ? productFilterDto.getSize() : DEFAULT_PAGE_SIZE;
        int offset = 0;
        BooleanBuilder builder = new BooleanBuilder();

        Long cursor = productFilterDto.getCursor();
        if (cursor != null) {
            builder.and(productFilter.id.loe(cursor));
        } else {
            int currentPage = productFilterDto.getPage() != null ? productFilterDto.getPage() : DEFAULT_PAGE_NUMBER;
            offset = currentPage == 0 ? 0 : (currentPage) * pageSize;
        }

        JPAQuery<ProductCursorResponseDto> query = queryFactory.select(
                        Projections.constructor(ProductCursorResponseDto.class,
                                productFilter.id,
                                productFilter.productCode
                        )
                )
                .from(productFilter)
                .where(
                        minPriceGoe(productFilterDto.getMinPrice()),
                        maxPriceLoe(productFilterDto.getMaxPrice()),
                        topCategoryEq(productFilterDto.getTopCategoryId()),
                        bottomCategoryEq(productFilterDto.getBottomCategoryIds()),
                        productFilter.isDeleted.isFalse()
                )
                .offset(offset)
                .limit(pageSize + 1);

        applySorting(query, productFilterDto.getSortBy(), id, price);

        List<ProductCursorResponseDto> content = query.fetch();

        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<ProductCursorResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    @Override
    public CursorPage<ProductCursorResponseDto> getSearchedProductList(ProductIdAndPriceDto productIdAndPriceDto) {

        int pageSize = productIdAndPriceDto.getSize() == null ? DEFAULT_PAGE_SIZE : productIdAndPriceDto.getSize();
        int offset = 0;

        if (productIdAndPriceDto.getCursor() == null) {
            int currentPage = productIdAndPriceDto.getPage() == null ? DEFAULT_PAGE_NUMBER : productIdAndPriceDto.getPage();
            offset = currentPage == 0 ? 0 : currentPage * pageSize;
        }

        JPAQuery<ProductCursorResponseDto> query = queryFactory.select(
                        Projections.constructor(ProductCursorResponseDto.class,
                                product.id,
                                product.productCode)
                ).from(productFilter)
                .innerJoin(product)
                .on(product.productCode.eq(productFilter.productCode))
                .where(minPriceGoe(productIdAndPriceDto.getMinPrice()),
                        maxPriceLoe(productIdAndPriceDto.getMaxPrice()),
                        productFilter.isDeleted.isFalse(),
                        product.isDeleted.isFalse(),
                        (product.productName.like("%" + productIdAndPriceDto.getQuery() + "%")
                        .or(productFilter.topCategoryName.like("%" + productIdAndPriceDto.getQuery() + "%")
                        .or(productFilter.bottomCategoryName.like("%" + productIdAndPriceDto.getQuery() + "%")))
                        )
                )
                .offset(offset)
                .limit(pageSize + 1);

        applySorting(query, productIdAndPriceDto.getSortBy(), productIdAndPriceDto.getId(), productIdAndPriceDto.getPrice());


        List<ProductCursorResponseDto
                > content = query.fetch();

        Long nextCursor = null;
        boolean hasNext = false;

        if (content.size() > pageSize) {
            nextCursor = content.get(pageSize).getId();
            content.remove(pageSize);
            hasNext = true;
        }

        return CursorPage.<ProductCursorResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();


    }


    private BooleanExpression minPriceGoe(Integer minPrice) {
        return minPrice != null ? productFilter.price.goe(minPrice) : null;
    }

    private BooleanExpression maxPriceLoe(Integer maxPrice) {
        return maxPrice != null ? productFilter.price.loe(maxPrice) : null;
    }

    private BooleanExpression topCategoryEq(Long topCategoryId) {
        return topCategoryId != null ? productFilter.topCategoryId.eq(topCategoryId) : null;
    }

    private BooleanExpression bottomCategoryEq(List<Long> bottomCategoryIds) {
        return bottomCategoryIds != null ? productFilter.bottomCategoryId.in(bottomCategoryIds) : null;
    }

    private void applySorting(JPAQuery<ProductCursorResponseDto> query,
                              String sortBy,
                              Long productListId,
                              int price) {

        BooleanBuilder builder = new BooleanBuilder();
        if (sortBy != null) {
            switch (sortBy) {
                case "price,asc":
                    builder.and(productFilter.price.gt(price))
                            .or(productFilter.price.eq(price).and(productFilter.id.loe(productListId)));
                    query.orderBy(productFilter.price.asc(), productFilter.id.desc());
                    break;
                case "price,desc":
                    builder.and(productFilter.price.lt(price))
                            .or(productFilter.price.eq(price).and(productFilter.id.loe(productListId)));
                    query.orderBy(productFilter.price.desc(), productFilter.id.desc());
                    break;
                case "createdAt,asc":
                    builder.and(productFilter.id.goe(productListId));
                    query.orderBy(productFilter.id.asc());
                    break;
                case "createdAt,desc":
                    builder.and(productFilter.id.loe(productListId));
                    query.orderBy(productFilter.id.desc());
                    break;
                default:
                    builder.and(productFilter.id.loe(productListId));
                    query.orderBy(productFilter.id.desc());
                    break;
            }
        } else {
            builder.and(productFilter.id.loe(productListId));
            query.orderBy(productFilter.id.desc());
        }

        query.where(builder);
    }
}