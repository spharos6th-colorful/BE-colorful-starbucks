package colorful.starbucks.batch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CategoryWithProductsAndQuantities {

    private Long categoryId;
    private String categoryName;
    private Map<Long, Integer> productCodeAndQuantityMap = new HashMap<>();

    public CategoryWithProductsAndQuantities(Long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public void addProductQuantity(Long productCode, int quantity) {
        productCodeAndQuantityMap.put(productCode, productCodeAndQuantityMap.getOrDefault(productCode, 0) + quantity);
    }
}
