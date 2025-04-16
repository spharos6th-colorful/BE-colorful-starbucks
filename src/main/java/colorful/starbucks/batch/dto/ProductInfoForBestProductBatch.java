package colorful.starbucks.batch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductInfoForBestProductBatch {

    private Long productCode;
    private Integer quantity;
    private Long categoryId;
    private String categoryName;

    public ProductInfoForBestProductBatch(Long productCode, Integer quantity, Long categoryId, String categoryName) {
        this.productCode = productCode;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
