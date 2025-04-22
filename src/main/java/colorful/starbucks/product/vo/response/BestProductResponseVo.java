package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BestProductResponseVo {

    private Long productCode;
    private Integer ranking;

    @Builder
    private BestProductResponseVo(Long productCode, Integer ranking) {
        this.productCode = productCode;
        this.ranking = ranking;
    }
}
