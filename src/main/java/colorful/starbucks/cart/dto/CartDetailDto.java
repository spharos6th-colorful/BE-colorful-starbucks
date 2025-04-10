package colorful.starbucks.cart.dto;

import colorful.starbucks.cart.domain.Cart;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartDetailDto {
    private Long cartId;
    private Boolean checked;
    private Long productCode;
    private Integer quantity;
    private String productDetailCode;
    private String carvingContent;

    @Builder
    public CartDetailDto(Long cartId,
                         Boolean checked,
                         Long productCode,
                         Integer quantity,
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
