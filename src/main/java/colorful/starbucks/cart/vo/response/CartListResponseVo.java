package colorful.starbucks.cart.vo.response;

import colorful.starbucks.cart.dto.CartProductDetailDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartListResponseVo {

    private int totalPages;
    private long totalElements;
    private List<CartProductDetailDto> productDetails;

    @Builder
    public CartListResponseVo(int totalPages,
                              long totalElements,
                              List<CartProductDetailDto> productDetails) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.productDetails = productDetails;
    }


}
