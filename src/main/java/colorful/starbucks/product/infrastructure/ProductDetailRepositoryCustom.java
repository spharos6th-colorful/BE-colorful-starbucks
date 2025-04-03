package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.dto.request.ProductDetailCodeAndQuantityRequestDto;

import java.util.Optional;

public interface ProductDetailRepositoryCustom {

    Optional<ProductDetail> findByProductCodeAndOptions(ProductDetailCodeAndQuantityRequestDto productDetailCodeAndQuantityRequestDto);
}
