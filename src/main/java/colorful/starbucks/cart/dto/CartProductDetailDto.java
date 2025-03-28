package colorful.starbucks.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductDetailDto {
    private Long cartId;
    private Boolean checked;
    private String productCode;
    private int quantity;
    private String productDetailCode;
    private String carvingContent;

    @Builder
    public CartProductDetailDto(Long cartId,
                                Boolean checked,
                                String productCode,
                                int quantity,
                                String productDetailCode,
                                String carvingContent) {
        this.cartId = cartId;
        this.checked = checked;
        this.productCode = productCode;
        this.quantity = quantity;
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
    }
}
