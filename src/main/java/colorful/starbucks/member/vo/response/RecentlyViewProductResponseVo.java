package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewProductResponseVo {

    private Long productCode;

    @Builder
    private RecentlyViewProductResponseVo(Long productCode) {
        this.productCode = productCode;
    }
}
