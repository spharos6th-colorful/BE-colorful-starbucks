package colorful.starbucks.cart.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartDetailResponseVo {

    private Boolean checked;
    private String productDetailCode;
    private Long productCode;
    private Integer quantity;
    private String carvingContent;

    @Builder
    private CartDetailResponseVo(Boolean checked,
                                 String productDetailCode,
                                 Long productCode,
                                 Integer quantity,
                                 String carvingContent) {
        this.checked = checked;
        this.productDetailCode = productDetailCode;
        this.productCode = productCode;
        this.quantity = quantity;
        this.carvingContent = carvingContent;
    }
}
