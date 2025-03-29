package colorful.starbucks.product.application;

import colorful.starbucks.cart.dto.response.CartProductOptionEditResponseDto;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductDetailCodeAndQuantityResponseDto;
import colorful.starbucks.product.dto.response.ProductDetailCreateResponseDto;
import colorful.starbucks.product.dto.response.ProductOptionListResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProductDetailService {
    ProductDetailCreateResponseDto createProductDetail(ProductDetailCreateRequestDto productDetailCreateRequestDto,
                                                       MultipartFile productDetailThumbnail);

    ProductOptionListResponseDto getProductOptionList(String productCode);

    ProductDetailCodeAndQuantityResponseDto getProductDetailWithOptions(
            String productCode, int sizeId, int colorId
    );

    CartProductOptionEditResponseDto getCartProductOption(String productDetailCode);
}
