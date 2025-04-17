package colorful.starbucks.product.dto;

import colorful.starbucks.product.vo.ProductFilterVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductFilterDto {

    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Long topCategoryId;
    private List<Long> bottomCategoryIds;
    private Integer page;
    private Integer size;
    private String sortBy;

    @Builder
    private ProductFilterDto(Long cursor,
                             Integer minPrice,
                             Integer maxPrice,
                             Long topCategoryId,
                             List<Long> bottomCategoryIds,
                             Integer page,
                             Integer size,
                             String sortBy) {
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.topCategoryId = topCategoryId;
        this.bottomCategoryIds = bottomCategoryIds;
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
    }

    public static ProductFilterDto from(ProductFilterVo productFilterVo) {
        return ProductFilterDto.builder()
                .cursor(productFilterVo.getCursor())
                .minPrice(productFilterVo.getMinPrice())
                .maxPrice(productFilterVo.getMaxPrice())
                .topCategoryId(productFilterVo.getTopCategoryId())
                .bottomCategoryIds(productFilterVo.getBottomCategoryIds())
                .page(productFilterVo.getPage())
                .size(productFilterVo.getSize())
                .sortBy(productFilterVo.getSortBy())
                .build();
    }
}
