package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartProductOptionEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionEditRequestDto {

    private String memberUuid;
    private long cartId;
    private long productCode;
    private String productDetailCode;
    private int quantity;

    @Builder
    private CartProductOptionEditRequestDto(String memberUuid,long cartId, long productCode, String productDetailCode, int quantity) {
        this.memberUuid = memberUuid;
        this.cartId = cartId;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public static CartProductOptionEditRequestDto from(CartProductOptionEditRequestVo cartProductOptionEditRequestVo, long cartId, String memberUuid) {
        return CartProductOptionEditRequestDto.builder()
                .memberUuid(memberUuid)
                .cartId(cartId)
                .productCode(cartProductOptionEditRequestVo.getProductCode())
                .productDetailCode(cartProductOptionEditRequestVo.getProductDetailCode())
                .quantity(cartProductOptionEditRequestVo.getQuantity())
                .build();
    }
}
