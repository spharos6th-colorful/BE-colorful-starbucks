package colorful.starbucks.cart.application;


import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.dto.request.CartProductOptionEditRequestDto;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartProductOptionEditResponseDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import colorful.starbucks.product.application.ProductDetailService;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductDetailRepository productDetailRepository;
    private final ProductDetailService productDetailService;

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
            }catch (Exception e){
                throw new RuntimeException("장바구니 상품 삭제에 실패했습니다.");
            }

        }

    }

    @Override
    public CartListResponseDto getCartList(String memberUuid, Pageable pageable) {
        return CartListResponseDto.from(
                cartRepository.findAllByMemberUuid(memberUuid, pageable)
        );
    }

    @Override
    public void editCartProductOptions(Long cartId, CartProductOptionEditRequestDto cartProductOptionEditRequestDto) {
        try {
            cartRepository.save(cartProductOptionEditRequestDto.toEntity(cartId,
                    cartProductOptionEditRequestDto.getProductDetailCode(),
                    cartProductOptionEditRequestDto.getQuantity()));
        }
        catch (Exception e){
            throw new RuntimeException("장바구니 상품 변경에 실패했습니다.");
        }
    }

}
