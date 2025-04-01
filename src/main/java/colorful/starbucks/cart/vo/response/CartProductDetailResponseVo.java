package colorful.starbucks.cart.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CartProductDetailResponseVo {

    private boolean checked;
    private String productDetailCode;
    private long productCode;
    private int quantity;
    private String carvingContent;

    @Builder
    private CartProductDetailResponseVo(boolean checked, String productDetailCode, long productCode, int quantity, String carvingContent) {
        this.checked = checked;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.carvingContent = carvingContent;
    }
}
