package colorful.starbucks.cart.application;

import colorful.starbucks.cart.dto.request.*;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartDetailResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {

    void addCart(List<CartAddRequestDto> cartAddRequestDtos);
    void removeCartList(List<CartDeleteRequestDto> cartDeleteRequestDtos);
    CartListResponseDto getCartList(String memberUuid, Pageable pageable);
    void editCartOptions(CartOptionEditRequestDto cartOptionEditRequestDto);
    CartDetailResponseDto getCartDetail(Long cartId);
    void updateCartChecked(List<CartCheckRequestDto> cartCheckRequestDtos);
    void removeAllCart(String memberUuid);
}