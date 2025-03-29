package colorful.starbucks.cart.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductOptionEditResponseVo {

    private Long colorId;
    private Long sizeId;

    @Builder
    public CartProductOptionEditResponseVo(Long colorId, Long sizeId) {
        this.colorId = colorId;
        this.sizeId = sizeId;
    }
}
