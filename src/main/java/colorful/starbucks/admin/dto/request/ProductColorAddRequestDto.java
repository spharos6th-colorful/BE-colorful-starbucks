package colorful.starbucks.admin.dto.request;

import colorful.starbucks.admin.domain.Color;
import colorful.starbucks.admin.vo.request.ProductColorAddRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductColorAddRequestDto {

    private String colorName;

    @Builder
    private ProductColorAddRequestDto(String colorName) {
        this.colorName = colorName;
    }

    public static ProductColorAddRequestDto from(ProductColorAddRequestVo productColorAddRequestVo) {
        return ProductColorAddRequestDto.builder()
                .colorName(productColorAddRequestVo.getColorName())
                .build();
    }

    public Color toEntity() {
        return Color.builder()
                .colorName(colorName)
                .build();
    }
}
