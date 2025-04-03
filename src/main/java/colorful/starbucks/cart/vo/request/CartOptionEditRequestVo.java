package colorful.starbucks.cart.vo.request;

import lombok.Getter;

@Getter
public class CartOptionEditRequestVo {

    private Long productCode;
    private String productDetailCode;
    private int quantity;

}
