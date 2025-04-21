package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MostRecentlyViewProductVo {

    private String productThumbnailUrl;

    @Builder
    private MostRecentlyViewProductVo(String productThumbnailUrl) {
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
