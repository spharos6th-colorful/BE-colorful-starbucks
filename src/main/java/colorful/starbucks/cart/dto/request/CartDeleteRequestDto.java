package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartDeleteRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartDeleteRequestDto {

    private Long id;
    private String memberUuid;

    @Builder
    public CartDeleteRequestDto(Long id, String memberUuid) {
        this.id = id;
        this.memberUuid = memberUuid;
    }

    public static CartDeleteRequestDto from(CartDeleteRequestVo cartDeleteRequestVo, String memberUuid) {
        return CartDeleteRequestDto.builder()
                .id(cartDeleteRequestVo.getId())
                .memberUuid(memberUuid)
                .build();
    }


    public static List<CartDeleteRequestDto> from(List<CartDeleteRequestVo> cartDeleteRequestVos, String memberUuid) {
        return cartDeleteRequestVos.stream()
                .map(vo -> from(vo, memberUuid))
                .collect(Collectors.toList());
    }


}
