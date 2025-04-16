package colorful.starbucks.search.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ElasticsearchResponseVo {

    private Long productCode;

    @Builder
    private ElasticsearchResponseVo(Long productCode) {
        this.productCode = productCode;
    }
}
