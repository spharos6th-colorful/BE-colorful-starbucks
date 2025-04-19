package colorful.starbucks.search.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "autocomplete_search_keyword", createIndex = false)
public class KeywordAutoCompleteDocument {

    @Id
    private String id;
    private String productName;

    public KeywordAutoCompleteDocument(String productName) {
        this.productName = productName;
    }
}
