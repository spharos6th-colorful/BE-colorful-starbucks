package colorful.starbucks.product.vo.response;

import colorful.starbucks.admin.dto.OptionDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductOptionListResponseVo {

    private OptionDto options;

    @Builder
    private ProductOptionListResponseVo(OptionDto options) {
        this.options = options;
    }
}
