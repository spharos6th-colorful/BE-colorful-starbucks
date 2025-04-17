package colorful.starbucks.member.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterestProductVo {

    private Long productCode;

    @Builder
    private InterestProductVo(Long productCode) {
        this.productCode = productCode;
    }
}
