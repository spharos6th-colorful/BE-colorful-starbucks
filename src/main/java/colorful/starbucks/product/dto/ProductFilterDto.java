package colorful.starbucks.product.dto;

import colorful.starbucks.product.vo.ProductFilterVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductFilterDto {

    private Long nextCursor;
    private Integer minPrice;
    private Integer maxPrice;
    private String topCategory;
    private String bottomCategory;
    private String sortBy;

    @Builder
    private ProductFilterDto(Long nextCursor,
                             Integer minPrice,
                             Integer maxPrice,
                             String topCategory,
                             String bottomCategory,
                             String sortBy) {
        this.nextCursor = nextCursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.topCategory = topCategory;
        this.bottomCategory = bottomCategory;
        this.sortBy = sortBy;
    }

    public static ProductFilterDto from(ProductFilterVo productFilterVo) {
        return ProductFilterDto.builder()
                .nextCursor(productFilterVo.getNextCursor())
                .minPrice(productFilterVo.getMinPrice())
                .maxPrice(productFilterVo.getMaxPrice())
                .topCategory(productFilterVo.getTopCategory())
                .bottomCategory(productFilterVo.getBottomCategory())
                .sortBy(productFilterVo.getSortBy())
                .build();
    }
}
