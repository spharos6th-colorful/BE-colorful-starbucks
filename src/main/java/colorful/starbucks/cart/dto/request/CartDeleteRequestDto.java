package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartDeleteRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartDeleteRequestDto {

    private Long id;

    @Builder
    public CartDeleteRequestDto(Long id) {
        this.id = id;
    }

    public static CartDeleteRequestDto from(CartDeleteRequestVo cartDeleteRequestVo) {
        return CartDeleteRequestDto.builder()
                .id(cartDeleteRequestVo.getId())
                .build();
    }


    public static List<CartDeleteRequestDto> fromList(List<CartDeleteRequestVo> cartDeleteRequestVos) {
        return cartDeleteRequestVos.stream()
                .map(CartDeleteRequestDto::from)
                .collect(Collectors.toList());
    }


}
