package colorful.starbucks.cart.presentation;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.cart.dto.request.*;
import colorful.starbucks.cart.vo.request.*;
import colorful.starbucks.cart.vo.response.CartListResponseVo;
import colorful.starbucks.cart.vo.response.CartDetailResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "장바구니 상품 담기 API",
            description = "장바구니에 상품을 추가하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createCart(Authentication authentication,
                                        @RequestBody CartAddListRequestVo cartAddListRequestVo) {

        cartService.addCart(CartAddRequestDto.of(cartAddListRequestVo, authentication.getName()));

        return ApiResponse.ok(
                "장바구니 담기를 성공적으로 완료했습니다",
                null
        );
    }

    @Operation(
            summary = "장바구니 옵션 변경 API",
            description = "장바구니에 있는 상품 옵션을 변경하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @PutMapping("/{cartId}")
    public ApiResponse<Void> editCartProductOptions(Authentication authentication,
                                                    @PathVariable Long cartId,
                                                    @RequestBody CartOptionEditRequestVo cartOptionEditRequestVo) {

        cartService.editCartOptions(CartOptionEditRequestDto.of(cartOptionEditRequestVo, cartId, authentication.getName()));
        return ApiResponse.ok(
                "장바구니 옵션 변경 완료했습니다.",
                null
        );
    }

    @Operation(
            summary = "장바구니 단건 상품 선택 여부 변경 API",
            description = "장바구니에 담긴 1개의 상품 선택 여부를 변경하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @PutMapping("/{cartId}/checked")
    public ApiResponse<Void> updateCartProductCheck(Authentication authentication,
                                                    @PathVariable Long cartId,
                                                    @RequestBody CartCheckRequestVo cartCheckRequestVo) {

        cartService.updateCartChecked(CartCheckRequestDto.of(cartCheckRequestVo, authentication.getName(), cartId));
        return ApiResponse.ok("장바구니 체크 변경이 완료 되었습니다.",
                null);
    }

    @Operation(
            summary = "장바구니 모든 상품 선택 여부 변경 API",
            description = "장바구니에 담긴 모든 상품 선택 여부를 변경하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @PutMapping("/checked")
    public ApiResponse<Void> updateCartAllChecked(Authentication authentication,
                                                  @RequestBody CartAllCheckRequestVo cartAllCheckRequestVo) {
        cartService.updateCartAllChecked(CartAllCheckRequestDto.of(cartAllCheckRequestVo, authentication.getName()));
        return ApiResponse.ok("장바구니 전체 체크 변경이 완료 되었습니다.",
                null);
    }

    @Operation(
            summary = "장바구니 목록 조회 API",
            description = "사용자의 장바구니 목록을 조회하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @GetMapping
    public ApiResponse<CartListResponseVo> getCartList(Authentication authentication,
                                                       @PageableDefault(size = 3) Pageable pageable) {

        return ApiResponse.ok(
                "장바구니 목록 조회를 성공적으로 완료했습니다",
                cartService.getCartList(authentication.getName(), pageable).toVo()
        );
    }

    @Operation(
            summary = "장바구니 상세 조회 API",
            description = "사용자의 장바구니 상세 정보를 조회하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @GetMapping("/{cartId}")
    public ApiResponse<CartDetailResponseVo> getCartProductDetails(@PathVariable Long cartId) {

        return ApiResponse.ok("장바구니 상품 상세 조회에 성공했습니다.",
                cartService.getCartDetail(cartId).toVo()
        );
    }

    @Operation(
            summary = "장바구니 전체 삭제 API",
            description = "사용자의 장바구니 목록을 모두 삭제하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @DeleteMapping("/all")
    public ApiResponse<Void> removeAllCart(Authentication authentication) {
        cartService.removeAllCart(authentication.getName());
        return ApiResponse.ok("장바구니 전체 삭제를 완료 했습니다.",
                null);
    }

    @Operation(
            summary = "장바구니 삭제 API",
            description = "사용자가 선택한 장바구니 목록을 삭제하는 API 입니다.",
            tags = {"CART-SERVICE"}
    )
    @DeleteMapping
    public ApiResponse<Void> removeCart(Authentication authentication,
                                        @RequestBody CartDeleteListRequestVo cartDeleteListRequestVo) {

        cartService.removeCartList(CartDeleteRequestDto.of(cartDeleteListRequestVo, authentication.getName()));
        return ApiResponse.of(
                HttpStatus.NO_CONTENT,
                "장바구니 상품 삭제를 완료했습니다",
                null
        );
    }

}
