package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartOptionEditRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartOptionEditRequestDto {

    private String memberUuid;
    private Long cartId;
    private Long productCode;
    private String productDetailCode;
    private int quantity;

    @Builder
    private CartOptionEditRequestDto(String memberUuid, Long cartId, Long productCode, String productDetailCode, int quantity) {
        this.memberUuid = memberUuid;
        this.cartId = cartId;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public static CartOptionEditRequestDto from(CartOptionEditRequestVo cartOptionEditRequestVo, Long cartId, String memberUuid) {
        return CartOptionEditRequestDto.builder()
                .memberUuid(memberUuid)
                .cartId(cartId)
                .productCode(cartOptionEditRequestVo.getProductCode())
                .productDetailCode(cartOptionEditRequestVo.getProductDetailCode())
                .quantity(cartOptionEditRequestVo.getQuantity())
                .build();
    }
}
