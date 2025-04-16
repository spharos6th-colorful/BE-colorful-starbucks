package colorful.starbucks.product.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;
import colorful.starbucks.product.dto.response.ProductOptionListResponseDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import colorful.starbucks.product.dto.response.ProductSimpleResponseDto;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductResponseDto createProduct(ProductCreateRequestDto productCreateRequestDto,
                                     MultipartFile productThumbnail,
                                     MultipartFile productImage);

    ProductResponseDto getProduct(Long productCode);

    ProductSimpleResponseDto getProductSimpleInformation(Long productCode);

    CursorPage<ProductCursorResponseDto> getProductsByFilter(ProductFilterDto productFilterDto);

    ProductOptionListResponseDto getProductOptionList(@RequestParam Long productCode);
}
