package colorful.starbucks.search.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchResponseVo {

    private Long productCode;
    private Long id;
    private Integer price;
    private String createdAt;

    @Builder
    private ElasticsearchResponseVo(Long productCode, Long id, Integer price, String createdAt) {
        this.productCode = productCode;
        this.id = id;
        this.price = price;
        this.createdAt = createdAt;
    }
}
