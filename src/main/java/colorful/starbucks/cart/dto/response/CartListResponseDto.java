package colorful.starbucks.cart.dto.response;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.dto.CartDetailDto;
import colorful.starbucks.cart.vo.response.CartListResponseVo;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

public class CartListResponseDto {

    private int totalPages;
    private long totalElements;
    private List<CartDetailDto> productDetails;

    @Builder
    public CartListResponseDto(int totalPages, long totalElements, List<CartDetailDto> productDetails) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.productDetails = productDetails;
    }

    public static CartListResponseDto from(Page<Cart> cartPage) {
        return CartListResponseDto.builder()
                .totalPages(cartPage.getTotalPages())
                .totalElements(cartPage.getTotalElements())
                .productDetails(
                        cartPage.getContent()
                                .stream()
                                .map(CartDetailDto::from)
                                .toList()
                )
                .build();
    }

    public CartListResponseVo toVo() {
        return CartListResponseVo.builder()
                .totalPages(totalPages)
                .totalElements(totalElements)
                .productDetails(productDetails)
                .build();
    }

}
