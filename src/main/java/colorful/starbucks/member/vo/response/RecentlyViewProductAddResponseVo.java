package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewProductAddResponseVo {

    private String productThumbnailUrl;

    @Builder
    private RecentlyViewProductAddResponseVo(String productThumbnailUrl) {
        this.productThumbnailUrl = productThumbnailUrl;
    }
}
