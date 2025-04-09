package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductDetailCodeAndQuantityResponseVo {

    private String productDetailCode;
    private Integer inventoryQuantity;

    @Builder
    private ProductDetailCodeAndQuantityResponseVo(String productDetailCode, Integer inventoryQuantity) {
        this.productDetailCode = productDetailCode;
        this.inventoryQuantity = inventoryQuantity;
    }
}
