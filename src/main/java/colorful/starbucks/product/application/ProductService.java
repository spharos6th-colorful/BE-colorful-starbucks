package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductCreateResponseDto create(ProductCreateRequestDto request,
                                    MultipartFile productThumbnail,
                                    MultipartFile productCommonImage);
}
