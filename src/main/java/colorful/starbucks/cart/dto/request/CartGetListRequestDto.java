package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartGetListRequestVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class CartGetListRequestDto {

    private String memberUuid;
    private Pageable pageable;

    @Builder
    private CartGetListRequestDto(String memberUuid, Pageable pageable) {
        this.memberUuid = memberUuid;
        this.pageable = pageable;
    }

    public static CartGetListRequestDto from(CartGetListRequestVo cartGetListRequestVo, String memberUuid) {
        return CartGetListRequestDto.builder()
                .memberUuid(memberUuid)
                .pageable(cartGetListRequestVo.getPageable())
                .build();
    }
}
