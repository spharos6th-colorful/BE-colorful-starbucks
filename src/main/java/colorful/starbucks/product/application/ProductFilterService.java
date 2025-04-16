package colorful.starbucks.product.application;

import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductFilterCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;

public interface ProductFilterService {

    void createProductCategoryList(ProductFilterCreateRequestDto productFilterCreateRequestDto);

    CursorPage<ProductCursorResponseDto> getFilteredProductList(ProductFilterDto productFilterDto);

    CursorPage<ProductCursorResponseDto> getSearchedProductList(ProductSearchListFilterDto productSearchListFilterDto);
}