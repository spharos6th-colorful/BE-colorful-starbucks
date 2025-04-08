package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.dto.ProductIdAndPriceDto;
import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.admin.dto.response.ProductCursorResponseDto;
import colorful.starbucks.common.util.CursorPage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static colorful.starbucks.admin.domain.QProductCategoryList.productCategoryList;
import static colorful.starbucks.product.domain.QProduct.product;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductReadRepositoryCustomImpl implements ProductReadRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public CursorPage<ProductCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto productCategoryListFilterDto,
                                                                       Long id,
                                                                       int price) {

        JPAQuery<ProductCursorResponseDto> query = queryFactory.select(
                        Projections.constructor(ProductCursorResponseDto.class,
                                productCategoryList.id,
                                productCategoryList.productCode
                        )
                )
                .from(productCategoryList)
                .where(
                        minPriceGoe(productCategoryListFilterDto.getMinPrice()),
                        maxPriceLoe(productCategoryListFilterDto.getMaxPrice()),
                        topCategoryEq(productCategoryListFilterDto.getTopCategoryId()),
                        bottomCategoryEq(productCategoryListFilterDto.getBottomCategoryIds()),
                        productCategoryList.isDeleted.isFalse()
                );

        applySorting(query, productCategoryListFilterDto.getSortBy(), id, price);

        int pageSize = productCategoryListFilterDto.getSize() == null ? DEFAULT_PAGE_SIZE : productCategoryListFilterDto.getSize();
        List<ProductCursorResponseDto> content = query.limit(pageSize + 1).fetch();

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
                ).from(productCategoryList)
                .join(product)
                .fetchJoin()
                .on(product.productCode.eq(productCategoryList.productCode))
                .where(minPriceGoe(productIdAndPriceDto.getMinPrice()),
                        maxPriceLoe(productIdAndPriceDto.getMaxPrice()),
                        productCategoryList.isDeleted.isFalse(),
                        product.isDeleted.isFalse(),
                        (product.productName.like("%" + productIdAndPriceDto.getQuery() + "%")
                        .or(productCategoryList.topCategoryName.like("%" + productIdAndPriceDto.getQuery() + "%")
                        .or(productCategoryList.bottomCategoryName.like("%" + productIdAndPriceDto.getQuery() + "%")))
                        )
                )
                .offset(offset)
                .limit(pageSize + 1);

        applySorting(query, productIdAndPriceDto.getSortBy(), productIdAndPriceDto.getId(), productIdAndPriceDto.getPrice());


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

    private void applySorting(JPAQuery<ProductCursorResponseDto> query,
                              String sortBy,
                              Long productListId,
                              int price) {

        BooleanBuilder builder = new BooleanBuilder();
        if (sortBy != null) {
            switch (sortBy) {
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