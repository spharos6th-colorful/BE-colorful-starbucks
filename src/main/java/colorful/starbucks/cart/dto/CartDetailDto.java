package colorful.starbucks.cart.dto;

import colorful.starbucks.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartDetailDto {
    private Long cartId;
    private boolean checked;
    private Long productCode;
    private int quantity;
    private String productDetailCode;
    private String carvingContent;

    @Builder
    public CartDetailDto(Long cartId,
                         boolean checked,
                         Long productCode,
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

    public static CartDetailDto from(Cart cart) {
        return CartDetailDto.builder()
                .cartId(cart.getId())
                .checked(cart.isChecked())
                .productCode(cart.getProductCode())
                .quantity(cart.getQuantity())
                .productDetailCode(cart.getProductDetailCode())
                .carvingContent(cart.getCarvingContent())
                .build();
    }
}
