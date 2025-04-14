package colorful.starbucks.search.application;

import colorful.starbucks.search.domain.ProductDocument;

import java.io.IOException;
import java.util.List;

public interface ProductSearchService {

    void syncAllToElasticsearch();
    List<ProductDocument> search(String keyword) throws IOException;
}
