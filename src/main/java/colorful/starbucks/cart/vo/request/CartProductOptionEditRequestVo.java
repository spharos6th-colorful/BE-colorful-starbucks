package colorful.starbucks.cart.vo.request;

import colorful.starbucks.cart.vo.CartProductOptionVo;
import lombok.Getter;

@Getter
public class CartProductOptionEditRequestVo {

    private Long cartId;
    private String productCode;
    private CartProductOptionVo cartProductOptionVo;


}
