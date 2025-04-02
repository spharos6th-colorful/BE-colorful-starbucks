package colorful.starbucks.cart.vo.request;

import lombok.Getter;

@Getter
public class CartProductOptionEditRequestVo {

    private long productCode;
    private String productDetailCode;
    private int quantity;

}
