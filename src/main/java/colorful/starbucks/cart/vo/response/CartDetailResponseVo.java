package colorful.starbucks.cart.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartDetailResponseVo {

    private boolean checked;
    private String productDetailCode;
    private Long productCode;
    private int quantity;
    private String carvingContent;

    @Builder
    private CartDetailResponseVo(boolean checked, String productDetailCode, Long productCode, int quantity, String carvingContent) {
        this.checked = checked;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.carvingContent = carvingContent;
    }
}
