package colorful.starbucks.admin.dto.request;

import colorful.starbucks.admin.domain.Size;
import colorful.starbucks.admin.vo.request.ProductSizeAddRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSizeAddRequestDto {

    private String sizeName;

    @Builder
    private ProductSizeAddRequestDto(String sizeName) {
        this.sizeName = sizeName;
    }

    public static ProductSizeAddRequestDto from(ProductSizeAddRequestVo productSizeAddRequestVo) {
        return ProductSizeAddRequestDto.builder()
                .sizeName(productSizeAddRequestVo.getSizeName())
                .build();
    }

    public Size toEntity() {
        return Size.builder()
                .sizeName(sizeName)
                .build();
    }
}
