package colorful.starbucks.cart.vo.response;

import colorful.starbucks.cart.dto.CartDetailDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartListResponseVo {

    private int totalPages;
    private long totalElements;
    private List<CartDetailDto> productDetails;

    @Builder
    public CartListResponseVo(int totalPages,
                              long totalElements,
                              List<CartDetailDto> productDetails) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.productDetails = productDetails;
    }


}
