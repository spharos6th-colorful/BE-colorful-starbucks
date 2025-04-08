package colorful.starbucks.admin.dto;

import colorful.starbucks.admin.vo.ProductCategoryListFilterVo;
import colorful.starbucks.admin.vo.ProductSearchListFilterVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductSearchListFilterDto {

    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private String sortBy;
    private Integer page;
    private Integer pageSize;

    @Builder
    private ProductSearchListFilterDto(Long cursor,
                                       Integer minPrice,
                                       Integer maxPrice,
                                       Integer size,
                                       String sortBy,
                                       Integer page,
                                       Integer pageSize) {
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.size = size;
        this.sortBy = sortBy;
        this.page = page;
        this.pageSize = pageSize;
    }

    public static ProductSearchListFilterDto from(ProductSearchListFilterVo productSearchListFilterVo) {
        return ProductSearchListFilterDto.builder()
                .cursor(productSearchListFilterVo.getCursor())
                .minPrice(productSearchListFilterVo.getMinPrice())
                .maxPrice(productSearchListFilterVo.getMaxPrice())
                .size(productSearchListFilterVo.getSize())
                .sortBy(productSearchListFilterVo.getSortBy())
                .page(productSearchListFilterVo.getPage())
                .pageSize(productSearchListFilterVo.getPageSize())
                .build();
    }
}

