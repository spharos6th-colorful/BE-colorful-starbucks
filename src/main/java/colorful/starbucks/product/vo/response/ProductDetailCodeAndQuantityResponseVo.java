package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDetailCodeAndQuantityResponseVo {

    private String productDetailCode;
    private int inventoryQuantity;

    @Builder
    private ProductDetailCodeAndQuantityResponseVo(String productDetailCode, int inventoryQuantity) {
        this.productDetailCode = productDetailCode;
        this.inventoryQuantity = inventoryQuantity;
    }
}
