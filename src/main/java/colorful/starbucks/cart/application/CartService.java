package colorful.starbucks.cart.application;

import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.dto.request.CartProductOptionEditRequestDto;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartProductDetailResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {

    void addCart(List<CartAddRequestDto> cartAddRequestDto);
    void removeCartList(String memberUuid, List<CartDeleteRequestDto> cartDeleteRequestDto);
    CartListResponseDto getCartList(String memberUuid, Pageable pageable);
    void editCartProductOptions(Long cartId, CartProductOptionEditRequestDto cartProductOptionEditRequestDto);
    CartProductDetailResponseDto getCartProductDetail(Long cartId);
}