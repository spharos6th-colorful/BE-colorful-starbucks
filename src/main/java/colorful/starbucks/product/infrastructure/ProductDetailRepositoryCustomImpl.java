package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static colorful.starbucks.product.domain.QProductDetail.productDetail;

@Repository
@RequiredArgsConstructor
public class ProductDetailRepositoryCustomImpl implements ProductDetailRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<ProductDetail> findByProductCodeAndOptions(String productCode, Long sizeId, Long colorId) {

        return Optional.ofNullable(
                queryFactory.selectFrom(productDetail)
                        .where(
                                productDetail.productCode.eq(productCode),
                                eqSizeId(sizeId),
                                eqColorId(colorId),
                                productDetail.isDeleted.isFalse()
                        ).fetchOne()
        );
    }

    BooleanExpression eqSizeId(Long sizeId) {
        return sizeId != null ? productDetail.sizeId.eq(sizeId) : null;
    }

    BooleanExpression eqColorId(Long colorId) {
        return colorId != null ? productDetail.colorId.eq(colorId) : null;
    }
}
