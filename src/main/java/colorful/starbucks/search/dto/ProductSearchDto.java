package colorful.starbucks.search.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSearchDto {
    private Long productCode;
    private String productName;
    private String topCategoryName;
    private String bottomCategoryName;
}