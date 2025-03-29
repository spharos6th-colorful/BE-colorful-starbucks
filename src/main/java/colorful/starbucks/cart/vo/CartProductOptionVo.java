package colorful.starbucks.cart.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionVo {

    private int colorId;
    private int sizeId;

    @Builder
    public CartProductOptionVo(int colorId, int sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}