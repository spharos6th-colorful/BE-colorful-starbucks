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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractMap;
import java.util.List;

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

        Cart cart = cartRepository.findByMemberUuidAndProductDetailCodeAndIsDeletedIsFalse(
                cartAddRequestDto.getMemberUuid(), cartAddRequestDto.getProductDetailCode()).orElseGet(() ->
                cartRepository.save(cartAddRequestDto.toEntity(cartAddRequestDto.getMemberUuid())
                ));
        cart.updateQuantity(cartAddRequestDto.getQuantity() + cart.getQuantity());

    }

    @Transactional
    @Override
    public void removeCartList(List<CartDeleteRequestDto> cartDeleteRequestDtos) {

        cartDeleteRequestDtos.stream()
                .map(cartProduct -> cartRepository.findByIdAndMemberUuid(cartProduct.getId(),cartProduct.getMemberUuid())
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

            Cart cart = cartRepository.findByIdAndMemberUuid(cartProductOptionEditRequestDto.getCartId(), cartProductOptionEditRequestDto.getMemberUuid())
                    .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

            cart.updateProductOption(cartProductOptionEditRequestDto.getProductDetailCode(),
                    cartProductOptionEditRequestDto.getQuantity());

    }

    @Override
    public CartProductDetailResponseDto getCartProductDetail(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        return CartProductDetailResponseDto.from(cart);

    }

    @Transactional
    @Override
    public void updateCartProductChecked(List<CartProductCheckRequestDto> cartProductCheckRequestDtos) {

        cartProductCheckRequestDtos.stream()
                .map(checkProduct-> { Cart cart = cartRepository.findByMemberUuidAndId(checkProduct.getMemberUuid(),checkProduct.getId())
                        .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
                    return new AbstractMap.SimpleEntry<>(cart, checkProduct.isChecked());
                })
                .forEach(entry -> entry.getKey().updateProductChecked(entry.getValue()));

    }

}

