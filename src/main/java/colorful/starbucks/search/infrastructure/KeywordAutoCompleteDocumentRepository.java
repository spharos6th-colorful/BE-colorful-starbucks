package colorful.starbucks.search.infrastructure;

import colorful.starbucks.search.domain.KeywordAutoCompleteDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KeywordAutoCompleteDocumentRepository extends ElasticsearchRepository<KeywordAutoCompleteDocument, String> {
}
