package colorful.starbucks.product.application;

import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.FilteredProductResponseDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductResponseDto create(ProductCreateRequestDto productCreateRequestDto,
                              MultipartFile productThumbnail,
                              MultipartFile productCommonImage);

    ProductResponseDto getProduct(String productCode);

    FilteredProductResponseDto getProductsByFilter(ProductFilterDto productFilterDto, Pageable pageable);
}
