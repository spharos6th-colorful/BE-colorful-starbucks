package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.ProductDetailCodeAndQuantityRequestDto;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductDetailCodeAndQuantityResponseDto;
import colorful.starbucks.product.dto.response.ProductDetailResponseDto;
import colorful.starbucks.product.dto.response.ProductOptionListResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductDetailService {

    ProductDetailResponseDto createProductDetail(ProductDetailCreateRequestDto productDetailCreateRequestDto,
                                                 MultipartFile productDetailThumbnail);

    ProductOptionListResponseDto getProductOptionList(String productCode);

    ProductDetailCodeAndQuantityResponseDto getProductDetailWithOptions(
            ProductDetailCodeAndQuantityRequestDto productDetailCodeAndQuantityRequestDto
    );

    ProductDetailResponseDto getProductDetail(Long productDetailCode);
}
