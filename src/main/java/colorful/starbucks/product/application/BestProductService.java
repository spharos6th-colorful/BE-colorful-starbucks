package colorful.starbucks.product.application;

import colorful.starbucks.admin.dto.ProductTopCategoryDtos;
import colorful.starbucks.product.dto.response.BestProductsResponseDtos;

public interface BestProductService {

    BestProductsResponseDtos getBestProductByCategoryId(Long categoryId);

    ProductTopCategoryDtos getBestProductCategories();
}
