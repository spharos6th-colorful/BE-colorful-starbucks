package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCursorResponseVo {

    private Long productCode;

    @Builder
    private ProductCursorResponseVo(Long productCode) {
        this.productCode = productCode;
    }
}
