package colorful.starbucks.cart.application;


import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.dto.request.CartProductCheckRequestDto;
import colorful.starbucks.cart.dto.request.CartProductOptionEditRequestDto;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartProductDetailResponseDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import colorful.starbucks.product.application.ProductDetailService;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Transactional
    @Override
    public void editCartProductOptions(Long cartId, CartProductOptionEditRequestDto cartProductOptionEditRequestDto) {
        try {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new EntityNotFoundException("카트아이디를 찾을 수 없습니다."));

            cart.updateProductOption(cartProductOptionEditRequestDto.getProductDetailCode(),
                    cartProductOptionEditRequestDto.getQuantity());
        }
        catch (Exception e){
            throw new RuntimeException("장바구니 상품 변경에 실패했습니다.");
        }
    }

    @Override
    public CartProductDetailResponseDto getCartProductDetail(Long cartId) {
        try {
            Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new EntityNotFoundException("카트 아이디를 찾을 수 없습니다."));
            return CartProductDetailResponseDto.from(cart);
        } catch (Exception e) {
            throw new RuntimeException("장바구니 상세 상품 조회에 실패했습니다.");
        }
    }

    @Transactional
    @Override
    public void updateCartProductChecked(List<CartProductCheckRequestDto> cartProductCheckRequestDto) {

        for (CartProductCheckRequestDto checkProduct : cartProductCheckRequestDto) {
            Cart cart = cartRepository.findById(checkProduct.getId())
                    .orElseThrow(() -> new RuntimeException("해당 장바구니 아이템을 찾을 수 없습니다."));
            cart.updateProductChecked(false);
        }
    }

}
