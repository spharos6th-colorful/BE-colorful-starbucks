package colorful.starbucks.search.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductSearchDto {
    private Long id;
    private Long productCode;
    private String productName;
    private String topCategoryName;
    private String bottomCategoryName;
    private Integer price;
    private LocalDateTime createdAt;

    public ProductSearchDto(Long id,
                            Long productCode,
                            String productName,
                            String topCategoryName,
                            String bottomCategoryName,
                            Integer price,
                            LocalDateTime createdAt) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.topCategoryName = topCategoryName;
        this.bottomCategoryName = bottomCategoryName;
        this.price = price;
        this.createdAt = createdAt;
    }
}