package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CartAddRequestDto {

    private String productDetailCode;
    private String carvingContent;
    private int price;

    @Builder
    private CartAddRequestDto(String productDetailCode,
                              String carvingContent,
                              int price) {
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
        this.price = price;
    }

    public static CartAddRequestDto from(CartAddRequestVo cartAddRequestVo) {
        return CartAddRequestDto.builder()
                .productDetailCode(cartAddRequestVo.getProductDetailCode())
                .carvingContent(cartAddRequestVo.getCarvingContent())
                .price(cartAddRequestVo.getPrice())
                .build();
    }
    public static List<CartAddRequestDto> fromList(List<CartAddRequestVo> cartAddRequestVos) {
        return cartAddRequestVos.stream()
                .map(CartAddRequestDto::from)
                .collect(Collectors.toList());
    }

    public Cart toEntity(String productDetailCode, String carvingContent, int price, String memberUuid) {
        return Cart.builder().
                productDetailCode(productDetailCode)
                .carvingContent(carvingContent)
                .price(price)
                .checked(true)
                .memberUuid(memberUuid)
                .build();
    }
}
