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
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.search.domain.KeywordAutoCompleteDocument;
import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.request.CategoryCountRequestDto;
import colorful.starbucks.search.dto.request.ElasticsearchRequestDto;
import colorful.starbucks.search.dto.response.AutoSearchListResponseDto;
import colorful.starbucks.search.dto.response.AutoSearchResponseDto;
import colorful.starbucks.search.dto.response.CategoryCountResponseDto;
import colorful.starbucks.search.dto.response.ElasticsearchResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ElasticsearchClient elasticsearchClient;

    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int PAGE_DEFAULT_SIZE = 10;

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

        SearchResponse<KeywordAutoCompleteDocument> response = elasticsearchClient.search(searchRequest, KeywordAutoCompleteDocument.class);

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

    @Override
    public CursorPage<ElasticsearchResponseDto> search(ElasticsearchRequestDto elasticsearchRequestDto) throws IOException {

        Query query = Query.of(q -> q
                .bool(b -> {
                    BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

                    if (elasticsearchRequestDto.getQuery() != null && !elasticsearchRequestDto.getQuery().isEmpty()) {

                        List<String> fields = List.of("productName.keyword", "topCategoryName.keyword", "bottomCategoryName.keyword");
                        fields.forEach(field -> boolBuilder.should(f -> f
                                .wildcard(t -> t
                                        .field(field)
                                        .value("*" + elasticsearchRequestDto.getQuery() + "*")
                                )
                        ));

                        boolBuilder.minimumShouldMatch("1");
                    }

                    if (elasticsearchRequestDto.getCategory() != null && !elasticsearchRequestDto.getCategory().isEmpty()) {
                        boolBuilder.filter(f -> f
                                .term(t -> t
                                        .field("topCategoryName.keyword")
                                        .value(elasticsearchRequestDto.getCategory())
                                )
                        );
                    }

                    if (elasticsearchRequestDto.getMinPrice() != null || elasticsearchRequestDto.getMaxPrice() != null) {
                        RangeQuery.Builder priceRange = new RangeQuery.Builder().field("price");
                        if (elasticsearchRequestDto.getMinPrice() != null)
                            priceRange.gte(JsonData.of(elasticsearchRequestDto.getMinPrice()));
                        if (elasticsearchRequestDto.getMaxPrice() != null)
                            priceRange.lte(JsonData.of(elasticsearchRequestDto.getMaxPrice()));
                        boolBuilder.filter(f -> f.range(priceRange.build()));
                    }

                    return boolBuilder;
                })
        );

        int pageSize = elasticsearchRequestDto.getSize() != null ? elasticsearchRequestDto.getSize() : PAGE_DEFAULT_SIZE;

        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder()
                .index("product_search")
                .query(query)
                .size(pageSize + 1)
                .sort(s -> s.field(f -> f.field("id").order(SortOrder.Desc)));

        if (elasticsearchRequestDto.getCursor() != null) {
            searchRequestBuilder.searchAfter(List.of(FieldValue.of(elasticsearchRequestDto.getCursor())));
        } else {
            int page = elasticsearchRequestDto.getPage() != null ? elasticsearchRequestDto.getPage() : DEFAULT_PAGE_NUMBER;
            int offset = page * pageSize;
            searchRequestBuilder.from(offset);
        }

        List<Hit<ProductDocument>> hits = elasticsearchClient.search(
                searchRequestBuilder.build(),
                ProductDocument.class
        ).hits().hits();

        boolean hasNext = hits.size() > pageSize;

        Long nextCursor = null;
        if (hasNext) {
            Hit<ProductDocument> lastHit = hits.get(pageSize - 1);
            nextCursor = Long.valueOf(lastHit.id());
        }

        List<ElasticsearchResponseDto> content = hits.stream()
                .limit(pageSize)
                .map(hit -> {
                    ProductDocument doc = hit.source();
                    return ElasticsearchResponseDto.builder()
                            .id(doc.getId())
                            .productCode(doc.getProductCode())
                            .createdAt(doc.getCreatedAt())
                            .price(doc.getPrice())
                            .build();
                })
                .collect(Collectors.toList());

        return CursorPage.<ElasticsearchResponseDto>builder()
                .content(content)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .build();
    }

    @Override
    public List<CategoryCountResponseDto> getCategoryAndCount(CategoryCountRequestDto categoryCountRequestDto) throws IOException {

        Query query = Query.of(q -> q
                .bool(b -> {
                    BoolQuery.Builder boolBuilder = new BoolQuery.Builder();

                    if (categoryCountRequestDto.getQuery() != null && !categoryCountRequestDto.getQuery().isEmpty()) {

                        List<String> fields = List.of("productName.keyword", "topCategoryName.keyword", "bottomCategoryName.keyword");
                        fields.forEach(field -> boolBuilder.should(f -> f
                                .wildcard(t -> t
                                        .field(field)
                                        .value("*" + categoryCountRequestDto.getQuery() + "*")
                                )
                        ));
                    }

                    if (categoryCountRequestDto.getMinPrice() != null || categoryCountRequestDto.getMaxPrice() != null) {
                        RangeQuery.Builder priceRange = new RangeQuery.Builder().field("price");
                        if (categoryCountRequestDto.getMinPrice() != null)
                            priceRange.gte(JsonData.of(categoryCountRequestDto.getMinPrice()));
                        if (categoryCountRequestDto.getMaxPrice() != null)
                            priceRange.lte(JsonData.of(categoryCountRequestDto.getMaxPrice()));
                        boolBuilder.filter(f -> f.range(priceRange.build()));
                    }

                    return boolBuilder;
                })
        );

        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder()
                .index("product_search")
                .query(query)
                .size(10000)
                .sort(s -> s.field(f -> f.field("id").order(SortOrder.Desc)));

        List<Hit<ProductDocument>> hits = elasticsearchClient.search(
                searchRequestBuilder.build(),
                ProductDocument.class
        ).hits().hits();

        HashMap<String, Integer> categoryCountMap = new HashMap<>();
        hits.forEach(hit -> {
            ProductDocument doc = hit.source();
            String topCategoryName = doc.getTopCategoryName();
            categoryCountMap.put(topCategoryName, categoryCountMap.getOrDefault(topCategoryName, 0) + 1);
        });

        return categoryCountMap.entrySet().stream()
                .map(entry -> CategoryCountResponseDto.builder()
                        .topCategoryName(entry.getKey())
                        .count(entry.getValue())
                        .build())
                .toList();
    }
}
