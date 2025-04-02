package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.dto.response.ProductCategoryCursorResponseDto;
import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.common.util.CursorPage;

public interface ProductCategoryListRepositoryCustom {

    CursorPage<ProductCategoryCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto productCategoryListFilterDto,
                                                                        Long id,
                                                                        int price);
}