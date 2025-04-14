package colorful.starbucks.batch;

import lombok.Getter;

@Getter
public class ProductCodeAndQuantity {

    private Long productCode;
    private int quantity;

    public ProductCodeAndQuantity(Long productCode, int quantity) {
        this.productCode = productCode;
        this.quantity = quantity;
    }
}
