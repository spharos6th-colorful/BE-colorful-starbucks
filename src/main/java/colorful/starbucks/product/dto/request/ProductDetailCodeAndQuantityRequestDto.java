package colorful.starbucks.product.dto.request;

import colorful.starbucks.product.vo.request.ProductDetailCodeAndQuantityRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailCodeAndQuantityRequestDto {

    private Long productDetailCode;
    private Long productCode;
    private Long sizeId;
    private Long colorId;
    private Integer quantity;

    @Builder
    private ProductDetailCodeAndQuantityRequestDto(Long productDetailCode,
                                                   Long productCode,
                                                   Long sizeId,
                                                   Long colorId,
                                                   Integer quantity) {
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.quantity = quantity;
    }

    public static ProductDetailCodeAndQuantityRequestDto from(
            ProductDetailCodeAndQuantityRequestVo productDetailCodeAndQuantityRequestVo) {
        return ProductDetailCodeAndQuantityRequestDto.builder()
                .productDetailCode(productDetailCodeAndQuantityRequestVo.getProductDetailCode())
                .productCode(productDetailCodeAndQuantityRequestVo.getProductCode())
                .sizeId(productDetailCodeAndQuantityRequestVo.getSizeId())
                .colorId(productDetailCodeAndQuantityRequestVo.getColorId())
                .quantity(productDetailCodeAndQuantityRequestVo.getQuantity())
                .build();
    }

}
