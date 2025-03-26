package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.response.ProductCreateResponseDto;
import colorful.starbucks.product.vo.ProductCreateRequestVo;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductCreateResponseDto create(ProductCreateRequestVo request,
                                    MultipartFile productThumbnail,
                                    MultipartFile productCommonImage);
}
