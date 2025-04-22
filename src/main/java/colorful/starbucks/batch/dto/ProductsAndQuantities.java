package colorful.starbucks.batch.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ProductsAndQuantities {

    private String categoryName;
    private Map<Long, Integer> productCodeAndQuantityMap = new HashMap<>();

    public ProductsAndQuantities(String categoryName) {
        this.categoryName = categoryName;
    }

    public void addProductQuantity(Long productCode, int quantity) {
        productCodeAndQuantityMap.put(productCode, productCodeAndQuantityMap.getOrDefault(productCode, 0) + quantity);
    }
}
