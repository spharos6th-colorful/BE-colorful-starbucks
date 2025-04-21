package colorful.starbucks.search.dto.request;

import colorful.starbucks.product.dto.request.CategoryCountRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryCountRequestDto {

    private String query;
    private Integer minPrice;
    private Integer maxPrice;

    @Builder
    private CategoryCountRequestDto(String query, Integer minPrice, Integer maxPrice) {
        this.query = query;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public static CategoryCountRequestDto from(CategoryCountRequestVo categoryCountRequestVo) {
        return CategoryCountRequestDto.builder()
                .query(categoryCountRequestVo.getQuery())
                .minPrice(categoryCountRequestVo.getMinPrice())
                .maxPrice(categoryCountRequestVo.getMaxPrice())
                .build();
    }
}
