package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartDeleteRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CartDeleteRequestDto {

    private Long id;
    private String memberUuid;

    @Builder
    public CartDeleteRequestDto(Long id, String memberUuid) {
        this.id = id;
        this.memberUuid = memberUuid;
    }

    public static CartDeleteRequestDto of(CartDeleteRequestVo cartDeleteRequestVo, String memberUuid) {
        return CartDeleteRequestDto.builder()
                .id(cartDeleteRequestVo.getId())
                .memberUuid(memberUuid)
                .build();
    }


    public static List<CartDeleteRequestDto> of(List<CartDeleteRequestVo> cartDeleteRequestVos, String memberUuid) {
        return cartDeleteRequestVos.stream()
                .map(vo -> of(vo, memberUuid))
                .collect(Collectors.toList());
    }


}
