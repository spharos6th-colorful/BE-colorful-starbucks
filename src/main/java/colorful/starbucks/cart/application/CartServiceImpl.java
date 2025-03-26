package colorful.starbucks.cart.application;


import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Override
    public void addCart(List<CartAddRequestDto> cartAddRequestDto) {
        for (CartAddRequestDto product : cartAddRequestDto) {

        }
    }
}
