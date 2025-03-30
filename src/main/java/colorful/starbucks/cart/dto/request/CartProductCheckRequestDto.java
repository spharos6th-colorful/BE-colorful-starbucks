package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartProductCheckRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartProductCheckRequestDto {

    private Long id;

    @Builder
    public CartProductCheckRequestDto(Long id) { this.id = id; }

    private static CartProductCheckRequestDto from(CartProductCheckRequestVo cartProductCheckRequestVo) {
        return CartProductCheckRequestDto.builder()
                .id(cartProductCheckRequestVo.getId())
                .build();
    }
    public static List<CartProductCheckRequestDto> fromList(List<CartProductCheckRequestVo> cartProductCheckRequestVos) {
        return cartProductCheckRequestVos.stream()
                .map(CartProductCheckRequestDto::from)
                .collect(Collectors.toList());
    }
}
