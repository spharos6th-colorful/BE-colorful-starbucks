package colorful.starbucks.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionDto {

    private Long colorId;
    private Long sizeId;

    @Builder
    public CartProductOptionDto(Long colorId, Long sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}