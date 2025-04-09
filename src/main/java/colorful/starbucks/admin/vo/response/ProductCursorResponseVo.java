package colorful.starbucks.admin.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCursorResponseVo {

    private Long id;
    private Long productCode;

    @Builder
    private ProductCursorResponseVo(Long id, Long productCode) {
        this.id = id;
        this.productCode = productCode;
    }
}
