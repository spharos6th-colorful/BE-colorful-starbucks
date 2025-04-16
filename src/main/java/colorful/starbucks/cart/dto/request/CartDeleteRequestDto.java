package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartDeleteListRequestVo;
import colorful.starbucks.cart.vo.request.CartDeleteRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CartDeleteRequestDto {

    private Long cartId;
    private String memberUuid;

    @Builder
    private CartDeleteRequestDto(Long cartId, String memberUuid) {
        this.cartId = cartId;
        this.memberUuid = memberUuid;
    }

    public static CartDeleteRequestDto of(CartDeleteRequestVo cartDeleteRequestVo, String memberUuid) {
        return CartDeleteRequestDto.builder()
                .cartId(cartDeleteRequestVo.getCartId())
                .memberUuid(memberUuid)
                .build();
    }


    public static List<CartDeleteRequestDto> of(CartDeleteListRequestVo cartDeleteListRequestVo, String memberUuid) {
        return cartDeleteListRequestVo.getCartIds().stream()
                .map(vo -> of(vo, memberUuid))
                .collect(Collectors.toList());
    }

}
