package colorful.starbucks.cart.application;

import colorful.starbucks.cart.dto.request.CartAddRequestDto;

import java.util.List;

public interface CartService {
    void addCart(List<CartAddRequestDto> cartAddRequestDto);

}