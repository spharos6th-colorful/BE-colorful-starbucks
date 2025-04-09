package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.vo.ProductCategoryListFilterVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductCategoryListFilterDto {

    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Long topCategoryId;
    private List<Long> bottomCategoryIds;
    private Integer size;
    private String sortBy;

    @Builder
    private ProductCategoryListFilterDto(Long cursor,
                                         Integer minPrice,
                                         Integer maxPrice,
                                         Long topCategoryId,
                                         List<Long> bottomCategoryIds,
                                         Integer size,
                                         String sortBy) {
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.topCategoryId = topCategoryId;
        this.bottomCategoryIds = bottomCategoryIds;
        this.size = size;
        this.sortBy = sortBy;
    }

    public static ProductCategoryListFilterDto from(ProductCategoryListFilterVo productCategoryListFilterVo) {
        return ProductCategoryListFilterDto.builder()
                .cursor(productCategoryListFilterVo.getCursor())
                .minPrice(productCategoryListFilterVo.getMinPrice())
                .maxPrice(productCategoryListFilterVo.getMaxPrice())
                .topCategoryId(productCategoryListFilterVo.getTopCategoryId())
                .bottomCategoryIds(productCategoryListFilterVo.getBottomCategoryIds())
                .size(productCategoryListFilterVo.getSize())
                .sortBy(productCategoryListFilterVo.getSortBy())
                .build();
    }
}
