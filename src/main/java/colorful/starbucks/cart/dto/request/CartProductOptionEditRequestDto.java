package colorful.starbucks.cart.dto.request;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.vo.request.CartProductOptionEditRequestVo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionEditRequestDto {

    private String productCode;
    private String productDetailCode;
    private int quantity;

    @Builder
    private CartProductOptionEditRequestDto(String productCode, String productDetailCode, int quantity) {
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.quantity = quantity;
    }

    public static CartProductOptionEditRequestDto from(CartProductOptionEditRequestVo cartProductOptionEditRequestVo) {
        return CartProductOptionEditRequestDto.builder()
                .productCode(cartProductOptionEditRequestVo.getProductCode())
                .productDetailCode(cartProductOptionEditRequestVo.getProductDetailCode())
                .quantity(cartProductOptionEditRequestVo.getQuantity())
                .build();
    }
}
