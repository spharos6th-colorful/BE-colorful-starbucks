package colorful.starbucks.cart.dto.response;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.response.CartProductDetailResponseVo;
import lombok.Builder;

public class CartProductDetailResponseDto {
    private boolean checked;
    private String productDetailCode;
    private String productCode;
    private int quantity;
    private String carvingContent;

    @Builder
    public CartProductDetailResponseDto(boolean checked, String productDetailCode, String productCode, int quantity, String carvingContent) {
        this.checked = checked;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.carvingContent = carvingContent;
    }

    public CartProductDetailResponseVo toVo() {
        return CartProductDetailResponseVo.builder()
                .checked(checked)
                .productDetailCode(productDetailCode)
                .productCode(productCode)
                .quantity(quantity)
                .carvingContent(carvingContent)
                .build();
    }

    public static CartProductDetailResponseDto from(Cart cart) {
        return CartProductDetailResponseDto.builder()
                .checked(cart.isChecked())
                .productDetailCode(cart.getProductDetailCode())
                .productCode(cart.getProductCode())
                .quantity(cart.getQuantity())
                .carvingContent(cart.getCarvingContent())
                .build();
    }


}
