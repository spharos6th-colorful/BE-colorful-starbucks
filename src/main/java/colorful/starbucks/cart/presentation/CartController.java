package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.cart.dto.request.*;
import colorful.starbucks.cart.vo.request.*;
import colorful.starbucks.cart.vo.response.CartListResponseVo;
import colorful.starbucks.cart.vo.response.CartProductDetailResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ApiResponse<Void> createCart(Authentication authentication,
                                        @RequestBody List<CartAddRequestVo> cartAddRequestVos) {

        cartService.addCart(CartAddRequestDto.fromList(cartAddRequestVos, authentication.getName()));

        return ApiResponse.ok(
                "장바구니 담기를 성공적으로 완료했습니다",
                null
        );
    }

    @DeleteMapping
    public ApiResponse<Void> deleteCart(Authentication authentication,
                                        @RequestBody List<CartDeleteRequestVo> cartDeleteRequestVos) {

        cartService.removeCartList(CartDeleteRequestDto.fromList(cartDeleteRequestVos, authentication.getName()));
        return ApiResponse.of(
                HttpStatus.NO_CONTENT,
                "장바구니 상품 삭제를 완료했습니다",
                null
        );
    }

    @GetMapping
    public ApiResponse<CartListResponseVo> getCartList(Authentication authentication,
                                                       @PageableDefault(size = 3) Pageable pageable) {

        return ApiResponse.ok(
                "장바구니 목록 조회를 성공적으로 완료했습니다",
                cartService.getCartList(authentication.getName(), pageable).toVo()
        );
    }

    @PutMapping("/{cartId}")
    public ApiResponse<Void> editCartProductOptions(Authentication authentication,
                                                    @PathVariable Long cartId,
                                                    @RequestBody CartProductOptionEditRequestVo cartProductOptionEditRequestVo){

        cartService.editCartProductOptions(CartProductOptionEditRequestDto.from(cartProductOptionEditRequestVo, cartId, authentication.getName()));
        return ApiResponse.ok(
                "장바구니 옵션 변경 완료했습니다.",
                null
        );
    }

    @GetMapping("/{cartId}")
    public ApiResponse<CartProductDetailResponseVo> getCartProductDetails(@PathVariable Long cartId) {

        return ApiResponse.ok("장바구니 상품 상세 조회에 성공했습니다.",
                cartService.getCartProductDetail(cartId).toVo()
        );
    }
    @PutMapping("/checked")
    public ApiResponse<CartProductCheckRequestVo> updateCartProductCheck(Authentication authentication,
                                                                         @RequestBody List<CartProductCheckRequestVo> cartProductCheckRequestVos) {

        cartService.updateCartProductChecked(CartProductCheckRequestDto.fromList(cartProductCheckRequestVos, authentication.getName()));
        return ApiResponse.ok("장바구니 상품의 체크 여부를 변경했습니다.",
        null);
    }
}
