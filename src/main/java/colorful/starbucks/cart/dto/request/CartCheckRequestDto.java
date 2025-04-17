package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartCheckRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CartCheckRequestDto {

    private Long cartId;
    private Boolean checked;
    private String memberUuid;

    @Builder
    public CartCheckRequestDto(Boolean checked, String memberUuid, Long cartId) {
        this.cartId = cartId;
        this.checked = checked;
        this.memberUuid = memberUuid;
    }

    public static CartCheckRequestDto of(CartCheckRequestVo cartCheckRequestVo, String memberUuid, Long cartId) {
        return CartCheckRequestDto.builder()
                .cartId(cartId)
                .checked(cartCheckRequestVo.getChecked())
                .memberUuid(memberUuid)
                .build();
    }

}
