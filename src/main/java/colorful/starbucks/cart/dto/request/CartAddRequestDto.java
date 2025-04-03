package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CartAddRequestDto {

    private String memberUuid;
    private String productDetailCode;
    private String carvingContent;
    private Long productCode;
    private int quantity;

    @Builder
    private CartAddRequestDto(String memberUuid,
                              String productDetailCode,
                              String carvingContent,
                              Long productCode,
                              int quantity) {
        this.memberUuid = memberUuid;
        this.productDetailCode = productDetailCode;
        this.carvingContent = carvingContent;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public static CartAddRequestDto from(CartAddRequestVo cartAddRequestVo, String memberUuid) {
        return CartAddRequestDto.builder()
                .memberUuid(memberUuid)
                .productDetailCode(cartAddRequestVo.getProductDetailCode())
                .carvingContent(cartAddRequestVo.getCarvingContent())
                .productCode(cartAddRequestVo.getProductCode())
                .quantity(cartAddRequestVo.getQuantity())
                .build();
    }
    public static List<CartAddRequestDto> from(List<CartAddRequestVo> cartAddRequestVos, String memberUuid) {
        return cartAddRequestVos.stream()
                .map(vo -> from(vo, memberUuid))
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
