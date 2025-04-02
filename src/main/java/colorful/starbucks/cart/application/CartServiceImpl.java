package colorful.starbucks.cart.application;


import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.dto.request.*;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartProductDetailResponseDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductDetailRepository productDetailRepository;

    @Transactional
    @Override
    public void addCart(List<CartAddRequestDto> cartAddRequestDtos) {

        cartAddRequestDtos.forEach(this::addCartProduct);
    }

    private void addCartProduct(CartAddRequestDto cartAddRequestDto) {

        Cart cart = cartRepository.findByMemberUuidAndProductDetailCode(
                cartAddRequestDto.getMemberUuid(), cartAddRequestDto.getProductDetailCode()).orElseGet(() ->
                cartRepository.save(cartAddRequestDto.toEntity(cartAddRequestDto.getMemberUuid())
                ));
        cart.updateQuantity(cartAddRequestDto.getQuantity() + cart.getQuantity());

    }

    @Transactional
    @Override
    public void removeCartList(List<CartDeleteRequestDto> cartDeleteRequestDtos) {

        cartDeleteRequestDtos.stream()
                .map(cartProduct -> cartRepository.findById(cartProduct.getId())
                        .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND)))
                .forEach(cart -> cartRepository.deleteById(cart.getId()));
    }

    @Override
    public CartListResponseDto getCartList(CartGetListRequestDto cartGetListRequestDto) {
        log.info("카트페이지: {}", cartGetListRequestDto.getPageable());
        return CartListResponseDto.from(
                cartRepository.findAllByMemberUuid(cartGetListRequestDto.getMemberUuid(), cartGetListRequestDto.getPageable())
        );
    }

    @Transactional
    @Override
    public void editCartProductOptions(CartProductOptionEditRequestDto cartProductOptionEditRequestDto) {
        try {
            Cart cart = cartRepository.findById(cartProductOptionEditRequestDto.getCartId())
                    .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

            cart.updateProductOption(cartProductOptionEditRequestDto.getProductDetailCode(),
                    cartProductOptionEditRequestDto.getQuantity());
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.DATABASE_ERROR);
        }
    }

    @Override
    public CartProductDetailResponseDto getCartProductDetail(Long cartId, String memberUuid) {
        Cart cart = cartRepository.findByMemberUuidAndId(memberUuid, cartId).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        return CartProductDetailResponseDto.from(cart);

    }

    @Transactional
    @Override
    public void updateCartProductChecked(List<CartProductCheckRequestDto> cartProductCheckRequestDto, String memberUuid) {
        try {
            for (CartProductCheckRequestDto checkProduct : cartProductCheckRequestDto) {
                Cart cart = cartRepository.findByMemberUuidAndId(memberUuid, checkProduct.getId())
                        .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
                cart.updateProductChecked(checkProduct.isChecked());
            }
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.DATABASE_ERROR);
        }
    }


}

