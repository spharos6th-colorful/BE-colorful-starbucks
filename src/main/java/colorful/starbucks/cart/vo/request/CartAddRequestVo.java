package colorful.starbucks.cart.vo.request;

import lombok.Getter;

@Getter
public class CartAddRequestVo {
    private Long productDetailCode;
    private String carvingContent;
    private Long productCode;
    private Integer quantity;
}
