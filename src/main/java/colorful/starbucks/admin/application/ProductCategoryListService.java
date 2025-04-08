package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.dto.response.ProductCursorResponseDto;
import colorful.starbucks.common.util.CursorPage;

public interface ProductCategoryListService {

    void createProductCategoryList(ProductCategoryListCreateRequestDto productCategoryListCreateRequestDto);

    CursorPage<ProductCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto productCategoryListFilterDto);

    CursorPage<ProductCursorResponseDto> getSearchedProductList(ProductSearchListFilterDto productSearchListFilterDto);
}