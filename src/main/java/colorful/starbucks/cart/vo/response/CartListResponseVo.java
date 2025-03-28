package colorful.starbucks.cart.vo.response;

import colorful.starbucks.cart.dto.CartProductDetailDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CartListResponseVo {

    private int totalPages;
    private int totalItems;
    private List<CartProductDetailDto> productDetails;

    @Builder
    public CartListResponseVo(int totalPages,
                              int totalItems,
                              List<CartProductDetailDto> productDetails) {
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.productDetails = productDetails;
    }
}
