package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartCheckRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CartCheckRequestDto {

    private Long id;
    private Boolean checked;
    private String memberUuid;

    @Builder
    public CartCheckRequestDto(Long id, Boolean checked, String memberUuid) {
        this.id = id;
        this.checked = checked;
        this.memberUuid = memberUuid;
    }

    public static CartCheckRequestDto of(CartCheckRequestVo cartCheckRequestVo, String memberUuid) {
        return CartCheckRequestDto.builder()
                .id(cartCheckRequestVo.getId())
                .checked(cartCheckRequestVo.getChecked())
                .memberUuid(memberUuid)
                .build();
    }

}
