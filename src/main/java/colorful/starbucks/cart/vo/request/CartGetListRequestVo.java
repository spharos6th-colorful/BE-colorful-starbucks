package colorful.starbucks.cart.vo.request;

import lombok.Getter;
import org.springframework.data.domain.Pageable;

@Getter
public class CartGetListRequestVo {

    private Pageable pageable;

}
