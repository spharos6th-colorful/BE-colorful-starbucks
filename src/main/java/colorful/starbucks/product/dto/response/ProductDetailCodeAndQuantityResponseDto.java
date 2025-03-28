package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.response.ProductDetailCodeAndQuantityResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailCodeAndQuantityResponseDto {

    private String productDetailCode;
    private int inventoryQuantity;

    @Builder
    private ProductDetailCodeAndQuantityResponseDto(String productDetailCode, int inventoryQuantity) {
        this.productDetailCode = productDetailCode;
        this.inventoryQuantity = inventoryQuantity;
    }

    public static ProductDetailCodeAndQuantityResponseDto from(ProductDetail productDetail) {
        return ProductDetailCodeAndQuantityResponseDto.builder()
                .productDetailCode(productDetail.getProductDetailCode())
                .inventoryQuantity(productDetail.getInventoryQuantity())
                .build();
    }

    public ProductDetailCodeAndQuantityResponseVo toVo() {
        return ProductDetailCodeAndQuantityResponseVo.builder()
                .productDetailCode(productDetailCode)
                .inventoryQuantity(inventoryQuantity)
                .build();
    }
}
