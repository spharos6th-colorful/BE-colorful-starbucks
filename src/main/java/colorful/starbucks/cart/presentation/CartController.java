package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import colorful.starbucks.cart.vo.request.CartDeleteRequestVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity create(@RequestBody List<CartAddRequestVo> cartAddRequestVos) {

        cartService.addCart(CartAddRequestDto.fromList(cartAddRequestVos));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/products/{memberUuid}")
    public ResponseEntity delete(@PathVariable String memberUuid,
                                 @RequestBody List<CartDeleteRequestVo> cartDeleteRequestVos) {

        cartService.removeCartList(memberUuid, CartDeleteRequestDto.fromList(cartDeleteRequestVos));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
