package colorful.starbucks.product.dto.response;

import colorful.starbucks.product.vo.response.FilteredProductResponseVo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class FilteredProductResponseDto {

    private List<ProductResponseDto> content;
    private int size;
    private boolean last;
    private boolean hasNext;
    private Long cursorProductCode;

    @Builder
    private FilteredProductResponseDto(List<ProductResponseDto> content,
                                       int size,
                                       boolean last,
                                       boolean hasNext,
                                       Long cursorProductCode) {
        this.content = content;
        this.size = size;
        this.last = last;
        this.hasNext = hasNext;
        this.cursorProductCode = cursorProductCode;
    }

    public static FilteredProductResponseDto of(Slice<ProductResponseDto> filteredProducts,
                                                Long cursorProductCode) {
        return FilteredProductResponseDto.builder()
                .content(filteredProducts.getContent())
                .size(filteredProducts.getSize())
                .last(filteredProducts.isLast())
                .hasNext(filteredProducts.hasNext())
                .cursorProductCode(cursorProductCode)
                .build();
    }


    public FilteredProductResponseVo toVo() {
        return FilteredProductResponseVo.builder()
                .content(content)
                .size(size)
                .last(last)
                .hasNext(hasNext)
                .cursorProductCode(cursorProductCode)
                .build();
    }
}
