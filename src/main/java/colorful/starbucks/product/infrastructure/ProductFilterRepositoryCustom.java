package colorful.starbucks.product.infrastructure;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;

public interface ProductFilterRepositoryCustom {

    CursorPage<ProductCursorResponseDto> getFilteredProductList(ProductFilterDto productFilterDto,
                                                                Long id,
                                                                int price);
}