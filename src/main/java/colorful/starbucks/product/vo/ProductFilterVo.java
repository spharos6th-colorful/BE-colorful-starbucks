package colorful.starbucks.product.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductFilterVo {

    private Long cursorProductCode;
    private Integer minPrice;
    private Integer maxPrice;
    private String topCategory;
    private String bottomCategory;
    private String sortBy;
}
