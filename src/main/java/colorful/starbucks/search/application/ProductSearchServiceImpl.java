package colorful.starbucks.search.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.ProductSearchDto;
import colorful.starbucks.search.dto.response.AutoSearchListResponseDto;
import colorful.starbucks.search.dto.response.AutoSearchResponseDto;
import colorful.starbucks.search.infrastructure.ProductDocumentRepository;
import colorful.starbucks.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;
    private final ProductDocumentRepository productDocumentRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public void syncAllToElasticsearch() {
        List<ProductSearchDto> searchDtoList = productRepository.findAllForSearch();

        List<ProductDocument> documents = searchDtoList.stream()
                .map(dto-> new ProductDocument(
                        dto.getProductCode(),
                        dto.getProductName(),
                        dto.getTopCategoryName(),
                        dto.getBottomCategoryName()
                ))
                .toList();
        productDocumentRepository.saveAll(documents);
    }

    //검색 기능
    @Override
    public List<ProductDocument> search(String keyword) throws IOException {
        Query query = Query.of(q->q
                .multiMatch(mm->mm
                .fields("productName","topCategoryName","bottomCategoryName")
                        .query(keyword)

                )
        );

        SearchRequest searchRequest = SearchRequest.of(
                s->s
                        .index("product_search")
                        .size(100)
                        .query(query)
        );

        SearchResponse<ProductDocument> response = elasticsearchClient.search(searchRequest, ProductDocument.class);

        return response.hits().hits().stream()
                .map(Hit::source)
                .toList();
    }

    //자동 검색
    @Override
    public AutoSearchListResponseDto getAutoSearchList(String keyword) throws IOException {

        Query query = Query.of(q -> q
                .multiMatch(mm -> mm
                        .fields("productName")
                        .query(keyword)
                )
        );

        SearchRequest searchRequest = SearchRequest.of(
                s -> s
                        .index("product_search")
                        .size(100)
                        .query(query)
        );

        SearchResponse<ProductDocument> response = elasticsearchClient.search(searchRequest, ProductDocument.class);

        List<AutoSearchResponseDto> results = response.hits().hits().stream()
                .map(Hit::source)
                .map(doc -> AutoSearchResponseDto.builder()
                        .productName(doc.getProductName())
                        .build()
                )
                .toList();

        return AutoSearchListResponseDto.builder()
                .autoSearchList(results)
                .build();


    }
}
