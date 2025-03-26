package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductDetailService {
    void createProductDetail(String productCode,
                             ProductDetailCreateRequestDto productDetailCreateRequestDto,
                             MultipartFile productDetailThumbnail);
}
