package colorful.starbucks.cart.vo.response;

import colorful.starbucks.cart.dto.CartDetailDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CartListResponseVo {

    private Integer totalPages;
    private Long totalElements;
    private List<CartDetailDto> productDetails;

    @Builder
    public CartListResponseVo(Integer totalPages,
                              Long totalElements,
                              List<CartDetailDto> productDetails) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.productDetails = productDetails;
    }


}
