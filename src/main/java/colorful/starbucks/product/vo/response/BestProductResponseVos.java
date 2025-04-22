package colorful.starbucks.product.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BestProductResponseVos {

    private List<BestProductResponseVo> bestProducts;

    @Builder
    private BestProductResponseVos(List<BestProductResponseVo> bestProducts) {
        this.bestProducts = bestProducts;
    }
}
