package colorful.starbucks.cart.application;


import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.dto.request.*;
import colorful.starbucks.cart.dto.response.CartListResponseDto;
import colorful.starbucks.cart.dto.response.CartDetailResponseDto;
import colorful.starbucks.cart.infrastructure.CartRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
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
        cart.addQuantity(cartAddRequestDto.getQuantity());

    }

    @Transactional
    @Override
    public void editCartOptions(CartOptionEditRequestDto cartOptionEditRequestDto) {

        Cart cart = cartRepository.findByIdAndMemberUuid(cartOptionEditRequestDto.getCartId(), cartOptionEditRequestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        cart.updateProductOption(cartOptionEditRequestDto.getProductDetailCode(),
                cartOptionEditRequestDto.getQuantity());

    }

    @Transactional
    @Override
    public void updateCartChecked(List<CartCheckRequestDto> cartCheckRequestDtos) {

        cartCheckRequestDtos
                .forEach(c -> {
                    cartRepository.findByIdAndMemberUuid(c.getId(), c.getMemberUuid())
                            .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
                            .updateProductChecked(c.isChecked());

                });
    }

    @Override
    public CartListResponseDto getCartList(String memberUuid, Pageable pageable) {

        return CartListResponseDto.from(
                cartRepository.findAllByMemberUuidAndIsDeletedIsFalse(memberUuid, pageable)
        );
    }

    @Override
    public CartDetailResponseDto getCartDetail(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
        return CartDetailResponseDto.from(cart);

    }


    @Transactional
    @Override
    public void removeCartList(List<CartDeleteRequestDto> cartDeleteRequestDtos) {

        cartDeleteRequestDtos.stream()
                .map(cartProduct -> cartRepository.findByIdAndMemberUuid(cartProduct.getId(), cartProduct.getMemberUuid())
                        .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND)))
                .forEach(cart -> cartRepository.deleteById(cart.getId()));
    }


    @Transactional
    @Override
    public void removeAllCart(String memberUuid) {
        cartRepository.deleteAllByMemberUuid(memberUuid);
    }

}

