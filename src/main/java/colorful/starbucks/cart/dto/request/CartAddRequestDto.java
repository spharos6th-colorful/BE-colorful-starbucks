package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import colorful.starbucks.cart.vo.request.*;

@Getter
@NoArgsConstructor
public class CartAddRequestDto {

    private String memberUuid;
    private Long productDetailCode;
    private String carvingContent;
    private Long productCode;
    private Integer quantity;

    @Builder
    private CartAddRequestDto(String memberUuid,
                              Long productDetailCode,
                              String carvingContent,
                              Long productCode,
                              Integer quantity) {
        this.memberUuid = memberUuid;
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public static CartAddRequestDto of(CartAddRequestVo cartAddRequestVo, String memberUuid) {
        return CartAddRequestDto.builder()
                .memberUuid(memberUuid)
                .productDetailCode(cartAddRequestVo.getProductDetailCode())
                .carvingContent(cartAddRequestVo.getCarvingContent())
                .productCode(cartAddRequestVo.getProductCode())
                .quantity(cartAddRequestVo.getQuantity())
                .build();
    }
    public static List<CartAddRequestDto> of(CartAddListRequestVo cartAddListRequestVo, String memberUuid) {
        return cartAddListRequestVo.getProducts().stream()
                .map(vo -> of(vo, memberUuid))
                .toList();
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
