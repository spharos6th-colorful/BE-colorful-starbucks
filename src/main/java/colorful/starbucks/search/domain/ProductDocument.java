package colorful.starbucks.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "product_search", createIndex = false)
public class ProductDocument {

    @Id
    private Long id;

    private Long productCode;
    private String productName;
    private String topCategoryName;
    private String bottomCategoryName;
    private Integer price;
    private String createdAt;
}