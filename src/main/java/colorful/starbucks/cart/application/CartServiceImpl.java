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

        cartRepository.findByMemberUuidAndProductDetailCodeAndIsDeletedIsFalse(
                cartAddRequestDto.getMemberUuid(), cartAddRequestDto.getProductDetailCode())
                .ifPresentOrElse(
                        cart -> cart.addQuantity(cartAddRequestDto.getQuantity()),
                        () -> cartRepository.save(cartAddRequestDto.toEntity(cartAddRequestDto.getMemberUuid()))
                );
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
    public void updateCartChecked(CartCheckRequestDto cartCheckRequestDto) {

        cartRepository.findByIdAndMemberUuid(cartCheckRequestDto.getCartId(), cartCheckRequestDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
                .updateProductChecked(cartCheckRequestDto.getChecked());

    }

    @Transactional
    @Override
    public void updateCartAllChecked(CartAllCheckRequestDto cartAllCheckRequestDto) {
        cartRepository.updateCheckedByMemberUuid(cartAllCheckRequestDto.getMemberUuid(),
                cartAllCheckRequestDto.getChecked());
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

        cartDeleteRequestDtos.forEach(cartProduct ->
                cartRepository.deleteByIdAndMemberUuid(cartProduct.getCartId(), cartProduct.getMemberUuid()));

    }

    @Transactional
    @Override
    public void removeAllCart(String memberUuid) {
        cartRepository.deleteAllByMemberUuid(memberUuid);
    }

    @Transactional
    @Override
    public void removeCartAfterOrder(String memberUuid, List<Long> productDetailCodes) {
        cartRepository.deleteCartsAfterOrder(memberUuid, productDetailCodes);
    }
}

