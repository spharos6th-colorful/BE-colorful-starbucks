package colorful.starbucks.search.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AutoSearchResponseVo {

    private String productName;

    @Builder
    private AutoSearchResponseVo(String productName) {
        this.productName = productName;
    }
}
