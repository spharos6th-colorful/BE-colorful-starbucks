package colorful.starbucks.product.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    CursorPage<ProductResponseDto> getProductsByFilter(ProductFilterDto filter, Long productId, int price, Pageable pageable);
}
