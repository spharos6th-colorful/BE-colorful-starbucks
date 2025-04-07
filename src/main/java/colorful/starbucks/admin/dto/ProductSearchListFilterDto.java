package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.vo.ProductCategoryListFilterVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductSearchListFilterDto {

    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private String sortBy;

    @Builder
    private ProductSearchListFilterDto(Long cursor,
                                       Integer minPrice,
                                       Integer maxPrice,
                                       Integer size,
                                       String sortBy) {
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.size = size;
        this.sortBy = sortBy;
    }

    public static ProductSearchListFilterDto from(ProductCategoryListFilterVo productCategoryListFilterVo) {
        return ProductSearchListFilterDto.builder()
                .cursor(productCategoryListFilterVo.getCursor())
                .minPrice(productCategoryListFilterVo.getMinPrice())
                .maxPrice(productCategoryListFilterVo.getMaxPrice())
                .size(productCategoryListFilterVo.getSize())
                .sortBy(productCategoryListFilterVo.getSortBy())
                .build();
    }
}

