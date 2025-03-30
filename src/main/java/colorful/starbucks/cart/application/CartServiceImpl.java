package colorful.starbucks.cart.application;


import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.dto.request.CartAddRequestDto;
import colorful.starbucks.cart.dto.request.CartDeleteRequestDto;
import colorful.starbucks.cart.dto.request.CartProductCheckRequestDto;
import colorful.starbucks.cart.dto.request.CartProductOptionEditRequestDto;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartProductDetailResponseDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductDetailRepository productDetailRepository;

    @Transactional
    @Override
    public void addCart(List<CartAddRequestDto> cartAddRequestDto, String memberUuid) {
        for (CartAddRequestDto cartProduct : cartAddRequestDto) {
            if (cartRepository.existsByMemberUuidAndProductDetailCode(memberUuid, cartProduct.getProductDetailCode())) {
                Cart cart = cartRepository.findByMemberUuidAndProductDetailCode(memberUuid, cartProduct.getProductDetailCode());
                if (cart.isDeleted()) {
                    cartRepository.save(cartProduct.toEntity(memberUuid));
                } else {
                    cart.updateQuantity(cart.getQuantity() + cartProduct.getQuantity());
                }
            } else {
                cartRepository.save(cartProduct.toEntity(memberUuid));
            }
        }
    }

    @Transactional
    @Override
    public void removeCartList(String memberUuid, List<CartDeleteRequestDto> cartDeleteRequestDto) {
        for(CartDeleteRequestDto cartProduct: cartDeleteRequestDto) {
            Cart cart = cartRepository.findByMemberUuidAndId(memberUuid, cartProduct.getId())
                            .orElseThrow(()-> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
            if (cart.isDeleted()) {
                throw new BaseException(ResponseStatus.ALREADY_DELETED_CART_ITEM);
            }
            cartRepository.deleteByMemberUuidAndId(memberUuid, cartProduct.getId());
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
    public void editCartProductOptions(Long cartId, String memberUuid, CartProductOptionEditRequestDto cartProductOptionEditRequestDto) {

        Cart cart = cartRepository.findByMemberUuidAndId(memberUuid, cartId)
                    .orElseThrow(()-> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        if (!productDetailRepository.existsByProductDetailCode((cartProductOptionEditRequestDto.getProductDetailCode()))) {
                throw new BaseException(ResponseStatus.NO_EXIST_OPTION);
        }
        cart.updateProductOption(cartProductOptionEditRequestDto.getProductDetailCode(),
                    cartProductOptionEditRequestDto.getQuantity());

    }

    @Override
    public CartProductDetailResponseDto getCartProductDetail(Long cartId, String memberUuid) {

        Cart cart = cartRepository.findByMemberUuidAndId(memberUuid, cartId).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        return CartProductDetailResponseDto.from(cart);

    }

    @Transactional
    @Override
    public void updateCartProductChecked(List<CartProductCheckRequestDto> cartProductCheckRequestDto, String memberUuid) {

        for (CartProductCheckRequestDto checkProduct : cartProductCheckRequestDto) {
            Cart cart = cartRepository.findByMemberUuidAndId(memberUuid, checkProduct.getId())
                    .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
            cart.updateProductChecked(checkProduct.isChecked());
        }
    }

}
