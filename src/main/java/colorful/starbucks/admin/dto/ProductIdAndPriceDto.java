package colorful.starbucks.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductIdAndPriceDto {

    private Long id;
    private int price;
    private String query;
    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer size;
    private String sortBy;
    private Integer page;
    private Integer pageSize;

    @Builder
    private ProductIdAndPriceDto(Long id, int price,
                                 String query,
                                 Long cursor,
                                 Integer minPrice,
                                 Integer maxPrice,
                                 Integer size,
                                 String sortBy,
                                 Integer page,
                                 Integer pageSize) {
        this.id = id;
        this.price = price;
        this.query = query;
        this.cursor = cursor;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.size = size;
        this.sortBy = sortBy;
        this.page = page;
        this.pageSize = pageSize;
    }

    public static ProductIdAndPriceDto of(ProductSearchListFilterDto productSearchListFilterDto,
                                          Long id,
                                          int price) {
        return ProductIdAndPriceDto.builder()
                .id(id)
                .price(price)
                .query(productSearchListFilterDto.getQuery())
                .cursor(productSearchListFilterDto.getCursor())
                .minPrice(productSearchListFilterDto.getMinPrice())
                .maxPrice(productSearchListFilterDto.getMaxPrice())
                .size(productSearchListFilterDto.getSize())
                .sortBy(productSearchListFilterDto.getSortBy())
                .page(productSearchListFilterDto.getPage())
                .pageSize(productSearchListFilterDto.getPageSize())
                .build();
    }
}
