package colorful.starbucks.product.vo.response;

import colorful.starbucks.product.dto.response.ProductResponseDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class FilteredProductResponseVo {

    private List<ProductResponseDto> content;
    private int size;
    private boolean last;
    private boolean hasNext;
    private String cursorProductCode;

    @Builder
    private FilteredProductResponseVo(List<ProductResponseDto> content,
                                      int size,
                                      boolean last,
                                      boolean hasNext,
                                      String cursorProductCode) {
        this.content = content;
        this.size = size;
        this.last = last;
        this.hasNext = hasNext;
        this.cursorProductCode = cursorProductCode;
    }
}
