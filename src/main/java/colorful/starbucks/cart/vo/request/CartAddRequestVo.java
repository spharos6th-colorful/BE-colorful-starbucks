package colorful.starbucks.cart.vo.request;

import lombok.Getter;

@Getter
public class CartAddRequestVo {
    private String productDetailCode;
    private String carvingContent;
    private int price;

}
