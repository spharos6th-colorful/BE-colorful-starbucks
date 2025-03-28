package colorful.starbucks.cart.application;


import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Transactional
    @Override
    public void addCart(List<CartAddRequestDto> cartAddRequestDto) {
        String memberUuid = UUID.randomUUID().toString();
        for (CartAddRequestDto product : cartAddRequestDto) {
            try{
                cartRepository.save(product.toEntity(memberUuid));
        }catch (Exception e){
                throw new RuntimeException("장바구니 등록에 실패했습니다.");
            }

        }
    }

    @Transactional
    @Override
    public void removeCartList(String memberUuid, List<CartDeleteRequestDto> cartDeleteRequestDto) {
        for(CartDeleteRequestDto cartProduct: cartDeleteRequestDto){
            try{
                cartRepository.deleteByMemberUuidAndId(memberUuid, cartProduct.getId());
                log.info("memberUuid:{}", memberUuid);
                log.info("상품번호:{}", cartProduct.getId());
            }catch (Exception e){
                throw new RuntimeException("장바구니 상품 삭제에 실패했습니다.");
            }

        }

    }
}
