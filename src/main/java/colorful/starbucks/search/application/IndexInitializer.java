package colorful.starbucks.search.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class IndexInitializer {

    private final ElasticsearchClient elasticsearchClient;

    @PostConstruct
    public void createIndexIfNotExists() throws IOException {
        String indexName = "product_search";

        boolean exists = elasticsearchClient.indices().exists(e -> e.index(indexName)).value();
        if (exists) {
            elasticsearchClient.indices().delete(d -> d.index(indexName));
            System.out.println("🗑 기존 인덱스 삭제");
        }

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
                            .properties("topCategoryName", p -> p.text(t -> t.analyzer("edge_ngram_analyzer")))
                            .properties("bottomCategoryName", p -> p.text(t -> t.analyzer("edge_ngram_analyzer")))
                    ))
            );


            System.out.println("✅ product_search 인덱스 생성 완료");

    }
}