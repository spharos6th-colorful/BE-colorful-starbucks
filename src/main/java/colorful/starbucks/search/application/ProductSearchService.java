package colorful.starbucks.search.application;

import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.response.AutoSearchListResponseDto;

import java.io.IOException;
import java.util.List;

public interface ProductSearchService {

    void syncAllToElasticsearch();
    List<ProductDocument> search(String keyword) throws IOException;
    AutoSearchListResponseDto getAutoSearchList(String keyword) throws IOException;
}
