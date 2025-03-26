package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/api/v1/carts")
public class CartController {

    @PostMapping
    public void create(@RequestBody List<CartAddRequestVo> cartAddRequestVos) {


    }

}
