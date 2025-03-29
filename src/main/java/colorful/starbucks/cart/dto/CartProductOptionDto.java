package colorful.starbucks.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionDto {

    private int colorId;
    private int sizeId;

    @Builder
    public CartProductOptionDto(int colorId, int sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}