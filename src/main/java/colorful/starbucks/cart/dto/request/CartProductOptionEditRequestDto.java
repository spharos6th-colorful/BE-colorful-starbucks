package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.dto.CartProductOptionDto;
import colorful.starbucks.cart.vo.CartProductOptionVo;
import colorful.starbucks.cart.vo.request.CartProductOptionEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionEditRequestDto {

    private Long cartId;
    private String productCode;
    private CartProductOptionVo cartProductOptionVo;

    @Builder
    public CartProductOptionEditRequestDto(Long cartId, String productCode, CartProductOptionVo cartProductOptionVo) {
        this.cartId = cartId;
        this.productCode = productCode;
        this.cartProductOptionVo = cartProductOptionVo;
    }
    public static CartProductOptionEditRequestDto from(CartProductOptionEditRequestVo cartProductEditRequestVo) {
        return CartProductOptionEditRequestDto.builder()
                .cartId(cartProductEditRequestVo.getCartId())
                .productCode(cartProductEditRequestVo.getProductCode())
                .cartProductOptionVo(cartProductEditRequestVo.getCartProductOptionVo())
                .build();
    }
}
