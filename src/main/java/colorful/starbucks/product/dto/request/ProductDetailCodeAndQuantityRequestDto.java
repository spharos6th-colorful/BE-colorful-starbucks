package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.vo.request.ProductDetailCodeAndQuantityRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailCodeAndQuantityRequestDto {

    private String productCode;
    private Long sizeId;
    private Long colorId;

    @Builder
    private ProductDetailCodeAndQuantityRequestDto(String productCode, Long sizeId, Long colorId) {
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.colorId = colorId;
    }

    public static ProductDetailCodeAndQuantityRequestDto from(
            ProductDetailCodeAndQuantityRequestVo productDetailCodeAndQuantityRequestVo) {
        return ProductDetailCodeAndQuantityRequestDto.builder()
                .productCode(productDetailCodeAndQuantityRequestVo.getProductCode())
                .sizeId(productDetailCodeAndQuantityRequestVo.getSizeId())
                .colorId(productDetailCodeAndQuantityRequestVo.getColorId())
                .build();
    }
}
