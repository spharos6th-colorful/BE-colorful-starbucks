package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.request.ProductColorAddRequestDto;
import colorful.starbucks.admin.dto.request.ProductSizeAddRequestDto;

public interface ProductOptionService {

    void createColorOption(ProductColorAddRequestDto productColorAddRequestDto);
    void createSizeOption(ProductSizeAddRequestDto productSizeAddRequestDto);
}
