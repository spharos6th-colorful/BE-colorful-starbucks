package colorful.starbucks.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryCountRequestVo {

    private String query;
    private Integer minPrice;
    private Integer maxPrice;
}
