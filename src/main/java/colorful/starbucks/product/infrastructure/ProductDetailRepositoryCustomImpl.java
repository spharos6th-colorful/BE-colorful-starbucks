package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.dto.request.ProductDetailCodeAndQuantityRequestDto;
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
    public Optional<ProductDetail> findByProductCodeAndOptions(ProductDetailCodeAndQuantityRequestDto productDetailCodeAndQuantityRequestDto) {

        return Optional.ofNullable(
                queryFactory.selectFrom(productDetail)
                        .where(
                                productDetail.productCode.eq(productDetailCodeAndQuantityRequestDto.getProductCode()),
                                eqSizeId(productDetailCodeAndQuantityRequestDto.getSizeId()),
                                eqColorId(productDetailCodeAndQuantityRequestDto.getColorId()),
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
