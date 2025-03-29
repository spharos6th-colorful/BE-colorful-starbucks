package colorful.starbucks.cart.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionVo {

    private Long colorId;
    private Long sizeId;

    @Builder
    public CartProductOptionVo(Long colorId, Long sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}