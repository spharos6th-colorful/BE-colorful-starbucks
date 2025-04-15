package colorful.starbucks.search.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.mapping.TypeMapping;
import co.elastic.clients.json.JsonData;
import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class IndexInitializer {

    private final ElasticsearchClient elasticsearchClient;

    @PostConstruct
    public void createIndexIfNotExists() throws IOException {
        String indexName = "product_search";

        boolean exists = elasticsearchClient.indices().exists(e -> e.index(indexName)).value();
        if (!exists) {

            JsonObject analysisJson = Json.createObjectBuilder()
                    .add("analyzer", Json.createObjectBuilder()
                            .add("custom_analyzer", Json.createObjectBuilder()
                                    .add("type", "custom")
                                    .add("tokenizer", "custom_ngram_tokenizer")
                                    .add("filter", Json.createArrayBuilder()
                                            .add("lowercase")
                                            .add("trim")
                                    )
                            )
                    )
                    .add("tokenizer", Json.createObjectBuilder()
                            .add("custom_ngram_tokenizer", Json.createObjectBuilder()
                                    .add("type", "ngram")
                                    .add("min_gram", 2)
                                    .add("max_gram", 3)
                                    .add("token_chars", Json.createArrayBuilder()
                                            .add("letter")
                                            .add("digit")
                                    )
                            )
                    )
                    .build();

            elasticsearchClient.indices().create(c -> c
                    .index(indexName)
                    .settings(s -> s
                            .withJson((InputStream) JsonData.of(Json.createObjectBuilder()
                                    .add("analysis", analysisJson)
                                    .build()
                            ).toJson().asJsonObject())
                    )
                    .mappings(TypeMapping.of(m -> m
                            .properties("productCode", p -> p.long_(l -> l.index(false)))
                            .properties("productName", p -> p.text(t -> t.analyzer("custom_analyzer")))
                            .properties("topCategoryName", p -> p.text(t -> t.analyzer("custom_analyzer")))
                            .properties("bottomCategoryName", p -> p.text(t -> t.analyzer("custom_analyzer")))
                    ))
            );

            System.out.println("✅ product_search 인덱스 생성 완료");
        } else {
            System.out.println("ℹ️ 인덱스 이미 존재함");
        }
    }
}