package colorful.starbucks.product.infrastructure;

import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.response.FilteredProductResponseDto;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {

    FilteredProductResponseDto getProductsByFilter(ProductFilterDto filter, Long productId, int price, Pageable pageable);
}
