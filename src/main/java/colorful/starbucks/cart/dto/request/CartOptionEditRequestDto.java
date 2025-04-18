package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartOptionEditRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartOptionEditRequestDto {

    private String memberUuid;
    private Long cartId;
    private Long productCode;
    private Long productDetailCode;
    private Integer quantity;

    @Builder
    private CartOptionEditRequestDto(String memberUuid,
                                     Long cartId,
                                     Long productCode,
                                     Long productDetailCode,
                                     Integer quantity) {
        this.memberUuid = memberUuid;
        this.cartId = cartId;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public static CartOptionEditRequestDto of(CartOptionEditRequestVo cartOptionEditRequestVo,
                                              Long cartId,
                                              String memberUuid) {
        return CartOptionEditRequestDto.builder()
                .memberUuid(memberUuid)
                .cartId(cartId)
                .productCode(cartOptionEditRequestVo.getProductCode())
                .productDetailCode(cartOptionEditRequestVo.getProductDetailCode())
                .quantity(cartOptionEditRequestVo.getQuantity())
                .build();
    }
}
