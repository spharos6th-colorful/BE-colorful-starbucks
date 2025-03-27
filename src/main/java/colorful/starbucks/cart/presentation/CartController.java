package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/carts")
    public ResponseEntity create(@RequestBody List<CartAddRequestVo> cartAddRequestVos) {

        cartService.addCart(CartAddRequestDto.fromList(cartAddRequestVos));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
