package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;

import java.util.Optional;

public interface ProductDetailRepositoryCustom {

    Optional<ProductDetail> findByProductCodeAndOptions(String productCode, Long sizeId, Long colorId);
}
