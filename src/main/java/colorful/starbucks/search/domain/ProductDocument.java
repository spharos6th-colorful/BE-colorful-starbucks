package colorful.starbucks.search.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Document(indexName = "product_search")
public class ProductDocument {

    @Id
    @JsonProperty("productCode")
    private Long productCode;

    @JsonProperty("productName")
    private String productName;

    @JsonProperty("topCategoryName")
    private String topCategoryName;

    @JsonProperty("bottomCategoryName")
    private String bottomCategoryName;
}