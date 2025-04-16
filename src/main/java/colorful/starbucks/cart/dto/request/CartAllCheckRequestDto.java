package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartAllCheckRequestVo;
import colorful.starbucks.cart.vo.request.CartCheckRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartAllCheckRequestDto {

    private Boolean checked;
    private String memberUuid;

    @Builder
    private CartAllCheckRequestDto(Boolean checked, String memberUuid) {
        this.memberUuid = memberUuid;
        this.checked = checked;
    }

    public static CartAllCheckRequestDto of(CartAllCheckRequestVo cartAllCheckRequestVo, String memberUuid) {
        return CartAllCheckRequestDto.builder()
                .memberUuid(memberUuid)
                .checked(cartAllCheckRequestVo.getChecked())
                .build();
    }

}
