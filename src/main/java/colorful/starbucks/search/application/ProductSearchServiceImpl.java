package colorful.starbucks.search.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.JsonData;
import colorful.starbucks.admin.dto.response.ProductCursorResponseDto;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.ProductSearchDto;
import colorful.starbucks.search.dto.request.ElasticsearchRequestDto;
import colorful.starbucks.search.dto.response.AutoSearchListResponseDto;
import colorful.starbucks.search.dto.response.AutoSearchResponseDto;
import colorful.starbucks.search.dto.response.ElasticsearchResponseDto;
import colorful.starbucks.search.infrastructure.ProductDocumentRepository;
import colorful.starbucks.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ProductRepository productRepository;
    private final ProductDocumentRepository productDocumentRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public void syncAllToElasticsearch() {
        List<ProductSearchDto> searchDtoList = productRepository.findAllForSearch();

        List<ProductDocument> documents = searchDtoList.stream()
                .map(dto-> new ProductDocument(
                        dto.getId(),
                        dto.getProductCode(),
                        dto.getProductName(),
                        dto.getTopCategoryName(),
                        dto.getBottomCategoryName(),
                        dto.getPrice(),
                        dto.getCreatedAt().toString()
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
                .bool(b -> b
                        .should(
                                Query.of(q1 -> q1.prefix(p -> p
                                        .field("productName")
                                        .value(keyword)
                                        .boost(2.0f)
                                ))
                        )
                )
        );

        SearchRequest searchRequest = SearchRequest.of(
                s -> s
                        .index("autocomplete_search_keyword")
                        .size(5)
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

    //검색 기능 with es
//    @Override
//    public CursorPage<ElasticsearchResponseDto> search2(ElasticsearchRequestDto elasticsearchRequestDto) throws IOException {
//        Query query = Query.of(q->q
//                .multiMatch(mm->mm
//                        .fields("productName","topCategoryName","bottomCategoryName")
//                        .query(elasticsearchRequestDto.getQuery())
//
//                )
//        );
//
//
//        SearchRequest searchRequest = SearchRequest.of(
//                s->s
//                        .index("product_search")
//                        .size(100)
//                        .query(query)
//        );
//        SearchResponse<ProductDocument> response = elasticsearchClient.search(searchRequest, ProductDocument.class);
//
//        return null;
//    }

    @Override
    public CursorPage<ElasticsearchResponseDto> search2(ElasticsearchRequestDto requestDto) throws IOException {
        log.info("들어온 size: {}", requestDto.getSize());
        Query query = Query.of(q -> q
                .bool(b -> {
                    BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

                    // 검색어 필터
                    if (requestDto.getQuery() != null && !requestDto.getQuery().isEmpty()) {
                        boolBuilder.must(m -> m
                                .multiMatch(mm -> mm
                                        .fields("productName", "topCategoryName", "bottomCategoryName")
                                        .query(requestDto.getQuery())
                                )
                        );
                    }

                    // 가격 필터
                    if (requestDto.getMinPrice() != null || requestDto.getMaxPrice() != null) {
                        RangeQuery.Builder priceRange = new RangeQuery.Builder().field("price");
                        if (requestDto.getMinPrice() != null) priceRange.gte(JsonData.of(requestDto.getMinPrice()));
                        if (requestDto.getMaxPrice() != null) priceRange.lte(JsonData.of(requestDto.getMaxPrice()));
                        boolBuilder.filter(f -> f.range(priceRange.build()));
                    }

                    return boolBuilder;
                })
        );

        int pageSize = requestDto.getSize() != null ? requestDto.getSize() : 20;

        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder()
                .index("product_search")
                .query(query)
                .size(pageSize + 1) // 다음 페이지가 있는지 확인용으로 1개 더 요청
                .sort(s -> s.field(f -> f.field("id").order(SortOrder.Desc)));

        if (requestDto.getCursor() != null) {
            searchRequestBuilder.searchAfter(List.of(FieldValue.of(requestDto.getCursor())));
        }

        SearchResponse<ProductDocument> response = elasticsearchClient.search(
                searchRequestBuilder.build(),
                ProductDocument.class
        );

        List<Hit<ProductDocument>> hits = response.hits().hits();
        boolean hasNext = hits.size() > pageSize;

        List<ElasticsearchResponseDto> content = hits.stream()
                .limit(pageSize)
                .map(hit -> {
                    ProductDocument doc = hit.source();
                    return ElasticsearchResponseDto.builder()
                            .productCode(doc.getProductCode())
                            .id(doc.getId())
                            .createdAt(doc.getCreatedAt())
                            .price(doc.getPrice())
                            .build();
                })
                .collect(Collectors.toList());

        Long nextCursor = null;
        if (hasNext) {
            Hit<ProductDocument> lastHit = hits.get(pageSize - 1);
            Object cursorValue = lastHit.sort().get(0)._get();
            if (cursorValue instanceof Number) {
                nextCursor = ((Number) cursorValue).longValue();
            }
        }

//        return new CursorPage<>(content, hasNext, nextCursor);
        return CursorPage.<ElasticsearchResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

}
