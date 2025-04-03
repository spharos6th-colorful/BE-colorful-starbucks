package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.ProductBottomCategoryDtos;
import colorful.starbucks.admin.dto.request.ProductBottomCategoryCreateRequestDto;
import org.springframework.data.domain.Pageable;

public interface ProductBottomCategoryService {

    void createBottomCategory(ProductBottomCategoryCreateRequestDto productBottomCategoryCreateRequestDto);

    ProductBottomCategoryDtos getBottomCategories(Long topCategoryId, Pageable pageable);
}
