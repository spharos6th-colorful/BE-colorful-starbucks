package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartAddRequestDto {

    private String productDetailCode;
    private String carvingContent;
    private String productCode;
    private int quantity;

    @Builder
    private CartAddRequestDto(String productDetailCode,
                              String carvingContent,
                              String productCode,
                              int quantity) {
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public static CartAddRequestDto from(CartAddRequestVo cartAddRequestVo) {
        return CartAddRequestDto.builder()
                .productDetailCode(cartAddRequestVo.getProductDetailCode())
                .carvingContent(cartAddRequestVo.getCarvingContent())
                .productCode(cartAddRequestVo.getProductCode())
                .quantity(cartAddRequestVo.getQuantity())
                .build();
    }
    public static List<CartAddRequestDto> fromList(List<CartAddRequestVo> cartAddRequestVos) {
        return cartAddRequestVos.stream()
                .map(CartAddRequestDto::from)
                .collect(Collectors.toList());
    }

    public Cart toEntity(String memberUuid) {
        return Cart.builder().
                productDetailCode(productDetailCode)
                .carvingContent(carvingContent)
                .productCode(productCode)
                .checked(true)
                .quantity(quantity)
                .memberUuid(memberUuid)
                .build();
    }
}
