package colorful.starbucks.admin.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductCategoryCursorResponseVo {

    private Long id;
    private Long productCode;

    @Builder
    private ProductCategoryCursorResponseVo(Long id, Long productCode) {
        this.id = id;
        this.productCode = productCode;
    }
}
