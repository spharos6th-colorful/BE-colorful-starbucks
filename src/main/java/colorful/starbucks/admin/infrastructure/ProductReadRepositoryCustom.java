package colorful.starbucks.admin.infrastructure;

import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.admin.dto.response.ProductCategoryCursorResponseDto;
import colorful.starbucks.admin.dto.response.ProductSearchCursorResponseDto;
import colorful.starbucks.common.util.CursorPage;

public interface ProductReadRepositoryCustom {

    CursorPage<ProductCategoryCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto productCategoryListFilterDto,
                                                                        Long id,
                                                                        int price);
    CursorPage<ProductSearchCursorResponseDto> getSearchedProductList(ProductSearchListFilterDto productSearchListFilterDto);
}