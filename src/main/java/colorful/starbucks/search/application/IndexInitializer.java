package colorful.starbucks.search.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import colorful.starbucks.product.infrastructure.ProductRepository;
import colorful.starbucks.search.domain.KeywordAutoCompleteDocument;
import colorful.starbucks.search.domain.ProductDocument;
import colorful.starbucks.search.dto.ProductSearchDto;
import colorful.starbucks.search.infrastructure.KeywordAutoCompleteDocumentRepository;
import colorful.starbucks.search.infrastructure.ProductDocumentRepository;
import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndexInitializer {

    private final ElasticsearchClient elasticsearchClient;
    private final ProductDocumentRepository productDocumentRepository;
    private final KeywordAutoCompleteDocumentRepository keywordAutoCompleteDocumentRepository;
    private final ProductRepository productRepository;

    @PostConstruct
    public void init() throws IOException {
        createSimpleSearchIndex();
        createdWithAutoComplete();
    }

    public void createdWithAutoComplete() throws IOException {
        String indexName = "autocomplete_search_keyword";
        boolean exists = elasticsearchClient.indices().exists(e -> e.index(indexName)).value();
        if (!exists) {
            JsonObject analysisJson = Json.createObjectBuilder()
                    .add("analyzer", Json.createObjectBuilder()
                            .add("edge_ngram_analyzer", Json.createObjectBuilder()
                                    .add("type", "custom")
                                    .add("tokenizer", "custom_edge_ngram_tokenizer")
                                    .add("filter", Json.createArrayBuilder()
                                            .add("lowercase")
                                    )
                            )
                    )
                    .add("tokenizer", Json.createObjectBuilder()
                            .add("custom_edge_ngram_tokenizer", Json.createObjectBuilder()
                                    .add("type", "edge_ngram")
                                    .add("min_gram", 1)
                                    .add("max_gram", 20)
                                    .add("token_chars", Json.createArrayBuilder()
                                            .add("letter")
                                            .add("digit")
                                    )
                            )
                    )
                    .build();

            String jsonString = Json.createObjectBuilder()
                    .add("analysis", analysisJson)
                    .build()
                    .toString();

            InputStream jsonStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));

            elasticsearchClient.indices().create(c -> c
                    .index(indexName)
                    .settings(s -> s.withJson(jsonStream))
                    .mappings(TypeMapping.of(m -> m
                            .properties("productName", p -> p.text(t -> t
                                    .analyzer("edge_ngram_analyzer")
                            ))
                    ))
            );


            List<String> productNames = productRepository.findAllProductNames();
            List<KeywordAutoCompleteDocument> keywords = productNames.stream()
                    .map(KeywordAutoCompleteDocument::new)
                    .toList();

            keywordAutoCompleteDocumentRepository.saveAll(keywords);
        }
    }

    public void createSimpleSearchIndex() throws IOException {
        String indexName = "product_search";

        boolean exists = elasticsearchClient.indices().exists(e -> e.index(indexName)).value();
        if (!exists) {
            JsonObject analysisJson = Json.createObjectBuilder()
                    .add("analyzer", Json.createObjectBuilder()
                            .add("ngram_analyzer", Json.createObjectBuilder()
                                    .add("type", "custom")
                                    .add("tokenizer", "nori_tokenizer")
                                    .add("filter", Json.createArrayBuilder()
                                            .add("lowercase")
                                    )
                            )
                    )
                    .build();

            String jsonString = Json.createObjectBuilder()
                    .add("analysis", analysisJson)
                    .build()
                    .toString();

            InputStream jsonStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));

            elasticsearchClient.indices().create(c -> c
                    .index(indexName)
                    .settings(s -> s.withJson(jsonStream))
                    .mappings(TypeMapping.of(m -> m
                            .properties("productName", p -> p.text(t -> t.analyzer("ngram_analyzer")))
                            .properties("topCategoryName", p -> p.text(t -> t.analyzer("ngram_analyzer")
                                    .fields("keyword", f -> f.keyword(k -> k.ignoreAbove(256)))
                            ))
                            .properties("bottomCategoryName", p -> p.text(t -> t.analyzer("ngram_analyzer")))
                    ))
            );

            List<ProductSearchDto> searchDtoList = productRepository.findAllForSearch();
            List<ProductDocument> documents = searchDtoList.stream()
                    .map(dto -> new ProductDocument(
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
    }
}