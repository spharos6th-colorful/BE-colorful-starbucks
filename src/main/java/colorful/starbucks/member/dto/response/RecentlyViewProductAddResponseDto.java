package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlyViewProductAddResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewProductAddResponseDto {

    private String productThumbnailUrl;

    @Builder
    private RecentlyViewProductAddResponseDto(String productThumbnailUrl) {
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static RecentlyViewProductAddResponseDto of(String productThumbnailUrl) {
        return RecentlyViewProductAddResponseDto.builder()
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }

    public RecentlyViewProductAddResponseVo toVo() {
        return RecentlyViewProductAddResponseVo.builder()
                .productThumbnailUrl(productThumbnailUrl)
                .build();
    }
}
