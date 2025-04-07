package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.dto.response.ProductCategoryCursorResponseDto;
import colorful.starbucks.admin.dto.response.ProductSearchCursorResponseDto;
import colorful.starbucks.common.util.CursorPage;

public interface ProductCategoryListService {

    void createProductCategoryList(ProductCategoryListCreateRequestDto productCategoryListCreateRequestDto);

    CursorPage<ProductCategoryCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto productCategoryListFilterDto);

    CursorPage<ProductSearchCursorResponseDto> getSearchedProductList(ProductSearchListFilterDto productSearchListFilterDto);
}