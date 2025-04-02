package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartProductOptionEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionEditRequestDto {

    private String memberUuid;
    private Long cartId;
    private Long productCode;
    private String productDetailCode;
    private int quantity;

    @Builder
    private CartProductOptionEditRequestDto(String memberUuid,Long cartId, Long productCode, String productDetailCode, int quantity) {
        this.memberUuid = memberUuid;
        this.cartId = cartId;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public static CartProductOptionEditRequestDto from(CartProductOptionEditRequestVo cartProductOptionEditRequestVo, Long cartId, String memberUuid) {
        return CartProductOptionEditRequestDto.builder()
                .memberUuid(memberUuid)
                .cartId(cartId)
                .productCode(cartProductOptionEditRequestVo.getProductCode())
                .productDetailCode(cartProductOptionEditRequestVo.getProductDetailCode())
                .quantity(cartProductOptionEditRequestVo.getQuantity())
                .build();
    }
}
