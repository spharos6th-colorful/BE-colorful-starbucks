package colorful.starbucks.cart.application;

import colorful.starbucks.cart.dto.request.*;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartProductDetailResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {

    void addCart(List<CartAddRequestDto> cartAddRequestDtos);
    void removeCartList(List<CartDeleteRequestDto> cartDeleteRequestDtos);
    CartListResponseDto getCartList(CartGetListRequestDto cartGetListRequestDto);
    void editCartProductOptions(CartProductOptionEditRequestDto cartProductOptionEditRequestDto);
    CartProductDetailResponseDto getCartProductDetail(Long cartId);
    void updateCartProductChecked(List<CartProductCheckRequestDto> cartProductCheckRequestDto, String memberUuid);
}