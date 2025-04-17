package colorful.starbucks.product.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductFilterVo {

    private Long cursor;
    private Integer minPrice;
    private Integer maxPrice;
    private Long topCategoryId;
    private List<Long> bottomCategoryIds;
    private Integer page;
    private Integer size;
    private String sortBy;
}
