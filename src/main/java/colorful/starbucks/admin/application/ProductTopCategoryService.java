package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.ProductTopCategoryDtos;
import colorful.starbucks.admin.dto.request.ProductTopCategoryCreateRequestDto;
import org.springframework.data.domain.Pageable;

public interface ProductTopCategoryService {

    void createTopCategory(ProductTopCategoryCreateRequestDto productTopCategoryCreateRequestDto);

    ProductTopCategoryDtos getTopCategories();
}
