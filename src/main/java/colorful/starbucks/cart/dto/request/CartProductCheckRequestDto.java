package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartProductCheckRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartProductCheckRequestDto {

    private Long id;
    private boolean checked;
    private String memberUuid;

    @Builder
    public CartProductCheckRequestDto(Long id, boolean checked, String memberUuid) {
        this.id = id;
        this.checked = checked;
        this.memberUuid = memberUuid;
    }

    private static CartProductCheckRequestDto from(CartProductCheckRequestVo cartProductCheckRequestVo, String memberUuid) {
        return CartProductCheckRequestDto.builder()
                .id(cartProductCheckRequestVo.getId())
                .checked(cartProductCheckRequestVo.isChecked())
                .memberUuid(memberUuid)
                .build();
    }
    public static List<CartProductCheckRequestDto> from(List<CartProductCheckRequestVo> cartProductCheckRequestVos, String memberUuid) {
        return cartProductCheckRequestVos.stream()
                .map(vo -> from(vo, memberUuid))
                .collect(Collectors.toList());
    }
}
