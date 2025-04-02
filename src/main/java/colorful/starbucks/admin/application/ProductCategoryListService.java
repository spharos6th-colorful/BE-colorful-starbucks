package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;

public interface ProductCategoryListService {

    void createProductCategoryList(ProductCategoryListCreateRequestDto productCategoryListCreateRequestDto);
}
