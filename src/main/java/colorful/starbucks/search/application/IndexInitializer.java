package colorful.starbucks.search.application;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class IndexInitializer {

    private final ElasticsearchClient elasticsearchClient;

    @PostConstruct
    public void createIndexIfNotExists() throws IOException {
        String indexName = "product_search";

        boolean exists = elasticsearchClient.indices().exists(e -> e.index(indexName)).value();
        if (!exists) {
            elasticsearchClient.indices().create(c -> c
                    .index(indexName)
                    .settings(s -> s
                            .analysis(a -> a
                                    .analyzer("custom_analyzer", analyzer -> analyzer
                                            .custom(ca -> ca
                                                    .tokenizer("standard")
                                                    .filter("lowercase")
                                            )
                                    )
                            )
                    )
                    .mappings(m -> m
                            .properties("productCode", p -> p
                                    .long_(l -> l.index(false))
                            )
                            .properties("productName", p -> p
                                    .text(t -> t.analyzer("custom_analyzer"))
                            )
                            .properties("topCategoryName", p -> p
                                    .text(t -> t.analyzer("custom_analyzer"))
                            )
                            .properties("bottomCategoryName", p -> p
                                    .text(t -> t.analyzer("custom_analyzer"))
                            )
                    )
            );

            System.out.println("✅ product_search 인덱스 생성 완료");
        } else {
            System.out.println("ℹ️ 인덱스 이미 존재함");
        }
    }
}
