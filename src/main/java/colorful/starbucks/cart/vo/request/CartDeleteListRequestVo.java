package colorful.starbucks.cart.vo.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CartDeleteListRequestVo {

    private List<CartDeleteRequestVo> cartIds;

}
