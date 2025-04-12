package colorful.starbucks.cart.dto.response;

import colorful.starbucks.cart.domain.Cart;
import colorful.starbucks.cart.dto.CartDetailDto;
import colorful.starbucks.cart.vo.response.CartListResponseVo;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
public class CartListResponseDto {

    private Integer totalPages;
    private Long totalElements;
    private List<CartDetailDto> productDetails;

    @Builder
    public CartListResponseDto(Integer totalPages, Long totalElements, List<CartDetailDto> productDetails) {
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
