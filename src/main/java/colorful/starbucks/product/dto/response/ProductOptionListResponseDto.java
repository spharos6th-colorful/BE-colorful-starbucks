package colorful.starbucks.product.dto.response;

import colorful.starbucks.admin.dto.response.ColorDto;
import colorful.starbucks.admin.dto.response.SizeDto;
import colorful.starbucks.admin.vo.OptionDto;
import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.vo.response.ProductOptionListResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductOptionListResponseDto {

    private OptionDto options;

    @Builder
    private ProductOptionListResponseDto(OptionDto options) {
        this.options = options;
    }

    public static ProductOptionListResponseDto from(List<ProductDetail> productDetails) {
        List<SizeDto> uniqueSizes = productDetails.stream()
                .map(p -> SizeDto.builder()
                        .sizeId(p.getSizeId())
                        .sizeName(p.getSizeName())
                        .build())
                .distinct()
                .toList();

        List<ColorDto> uniqueColors = productDetails.stream()
                .map(p -> ColorDto.builder()
                        .colorId(p.getColorId())
                        .colorName(p.getColorName())
                        .build())
                .distinct()
                .toList();

        return new ProductOptionListResponseDto(
                OptionDto.builder()
                        .size(uniqueSizes)
                        .color(uniqueColors)
                        .build());
    }

    public ProductOptionListResponseVo toVo() {
        return ProductOptionListResponseVo.builder()
                .options(options)
                .build();
    }
}
