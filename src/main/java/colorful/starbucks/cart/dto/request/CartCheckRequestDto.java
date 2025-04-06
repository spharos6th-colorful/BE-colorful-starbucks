package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartCheckRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartCheckRequestDto {

    private Long id;
    private boolean checked;
    private String memberUuid;

    @Builder
    public CartCheckRequestDto(Long id, boolean checked, String memberUuid) {
        this.id = id;
        this.checked = checked;
        this.memberUuid = memberUuid;
    }

    public static CartCheckRequestDto of(CartCheckRequestVo cartCheckRequestVo, String memberUuid) {
        return CartCheckRequestDto.builder()
                .id(cartCheckRequestVo.getId())
                .checked(cartCheckRequestVo.isChecked())
                .memberUuid(memberUuid)
                .build();
    }

}
