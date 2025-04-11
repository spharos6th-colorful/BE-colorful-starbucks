package colorful.starbucks.cart.vo.request;

import lombok.Getter;

@Getter
public class CartOptionEditRequestVo {

    private Long productCode;
    private Long productDetailCode;
    private Integer quantity;

}
