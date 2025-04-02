package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartProductOptionEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionEditRequestDto {

    private long cartId;
    private long productCode;
    private String productDetailCode;
    private int quantity;

    @Builder
    private CartProductOptionEditRequestDto(long productCode, String productDetailCode, int quantity, long cartId) {
        this.cartId = cartId;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public static CartProductOptionEditRequestDto from(CartProductOptionEditRequestVo cartProductOptionEditRequestVo, long cartId) {
        return CartProductOptionEditRequestDto.builder()
                .cartId(cartId)
                .productCode(cartProductOptionEditRequestVo.getProductCode())
                .productDetailCode(cartProductOptionEditRequestVo.getProductDetailCode())
                .quantity(cartProductOptionEditRequestVo.getQuantity())
                .build();
    }
}
