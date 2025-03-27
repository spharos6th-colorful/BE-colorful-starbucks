package colorful.starbucks.cart.application;


import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Transactional
    @Override
    public void addCart(List<CartAddRequestDto> cartAddRequestDto) {
        String memberUuid = UUID.randomUUID().toString();
        for (CartAddRequestDto product : cartAddRequestDto) {
            try{
                cartRepository.save(product.toEntity(product.getProductDetailCode(),
                        product.getCarvingContent(),
                        product.getPrice(),
                        memberUuid));
        }catch (Exception e){
                throw new RuntimeException("장바구니 등록에 실패했습니다.");
            }

        }
    }
}
