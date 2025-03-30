package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.dto.request.CartProductOptionEditRequestDto;
import colorful.starbucks.cart.vo.request.CartAddRequestVo;
import colorful.starbucks.cart.vo.request.CartDeleteRequestVo;
import colorful.starbucks.cart.vo.request.CartProductOptionEditRequestVo;
import colorful.starbucks.cart.vo.response.CartListResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ApiResponse<Void> create(@RequestBody List<CartAddRequestVo> cartAddRequestVos) {

        cartService.addCart(CartAddRequestDto.fromList(cartAddRequestVos));
        return ApiResponse.of(
                HttpStatus.OK,
                "장바구니 목록 조회를 성공적으로 완료했습니다",
                null
        );
    }

    @DeleteMapping("/{memberUuid}")
    public ApiResponse<Void> delete(@PathVariable String memberUuid,
                                 @RequestBody List<CartDeleteRequestVo> cartDeleteRequestVos) {

        cartService.removeCartList(memberUuid, CartDeleteRequestDto.fromList(cartDeleteRequestVos));
        return ApiResponse.of(
                HttpStatus.NO_CONTENT,
                "장바구니 상품 삭제를 완료했습니다",
                null
        );
    }

    @GetMapping("/{memberUuid}")
    public ApiResponse<CartListResponseVo> getCartProducts(@PathVariable String memberUuid,
                                                           @PageableDefault(size = 3) Pageable pageable) {
        return ApiResponse.of(
                HttpStatus.OK,
                "장바구니 목록 조회를 성공적으로 완료했습니다",
                cartService.getCartList(memberUuid, pageable).toVo()
        );
    }

    @PatchMapping("/{memberUuid}/{cartId}")
    public ApiResponse<Void> editCartProductOptions(@PathVariable String memberUuid,
                                                                  @PathVariable Long cartId,
                                                                  @RequestBody CartProductOptionEditRequestVo cartProductOptionEditRequestVo){

        cartService.editCartProductOptions(cartId, CartProductOptionEditRequestDto.from(cartProductOptionEditRequestVo));
        return ApiResponse.of(
                HttpStatus.OK,
                "장바구니 옵션 변경 완료했습니다.",
                null
        );
    }


}
