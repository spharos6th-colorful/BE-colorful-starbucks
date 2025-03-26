package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
