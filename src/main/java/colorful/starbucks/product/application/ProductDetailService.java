package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductDetailCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductDetailService {
    ProductDetailCreateResponseDto createProductDetail(String productCode,
                                                       ProductDetailCreateRequestDto productDetailCreateRequestDto,
                                                       MultipartFile productDetailThumbnail);
}
