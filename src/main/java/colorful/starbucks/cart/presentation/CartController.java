package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @PostMapping("/cart")
    public void create(@RequestBody List<CartAddRequestVo> cartAddRequestVos) {

        cartService.addCart(CartAddRequestDto.fromList(cartAddRequestVos));

    }
}
