package colorful.starbucks.search.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "product_search")
public class ProductDocument {

    @Id
    private Long productCode;

    private String productName;
    private String topCategoryName;
    private String bottomCategoryName;
}