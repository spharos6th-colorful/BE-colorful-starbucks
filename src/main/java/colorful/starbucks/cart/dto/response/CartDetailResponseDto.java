package colorful.starbucks.cart.dto.response;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.response.CartDetailResponseVo;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CartDetailResponseDto {
    private Boolean checked;
    private Long productDetailCode;
    private Long productCode;
    private Integer quantity;
    private String carvingContent;

    @Builder
    private CartDetailResponseDto(Boolean checked,
                                  Long productDetailCode,
                                  Long productCode,
                                  Integer quantity,
                                  String carvingContent) {
        this.checked = checked;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.carvingContent = carvingContent;
    }

    public CartDetailResponseVo toVo() {
        return CartDetailResponseVo.builder()
                .checked(checked)
                .productDetailCode(productDetailCode)
                .productCode(productCode)
                .quantity(quantity)
                .carvingContent(carvingContent)
                .build();
    }

    public static CartDetailResponseDto from(Cart cart) {
        return CartDetailResponseDto.builder()
                .checked(cart.isChecked())
                .productDetailCode(cart.getProductDetailCode())
                .productCode(cart.getProductCode())
                .quantity(cart.getQuantity())
                .carvingContent(cart.getCarvingContent())
                .build();
    }


}
