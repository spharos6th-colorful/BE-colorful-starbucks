package colorful.starbucks.cart.application;

import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;

import java.util.List;

public interface CartService {
    void addCart(List<CartAddRequestDto> cartAddRequestDto);
    void removeCartList(String memberUuid, List<CartDeleteRequestDto> cartDeleteRequestDto);
}