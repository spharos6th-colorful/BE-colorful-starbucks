package colorful.starbucks.cart.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartAddRequestVo {
    private String productDetailCode;
    private String carvingContent;
    private int price;

}
