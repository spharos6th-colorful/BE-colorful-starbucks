package colorful.starbucks.member.dto.request;

import colorful.starbucks.member.vo.request.RecentlyViewProductAddRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlyViewProductAddRequestDto {

    private String memberUuid;
    private Long productCode;
    private String productThumbnailUrl;

    @Builder
    private RecentlyViewProductAddRequestDto(String memberUuid,
                                             Long productCode,
                                             String productThumbnailUrl) {
        this.memberUuid = memberUuid;
        this.productCode = productCode;
        this.productThumbnailUrl = productThumbnailUrl;
    }

    public static RecentlyViewProductAddRequestDto of(String memberUuid,
                                                      RecentlyViewProductAddRequestVo recentlyViewProductAddRequestVo) {
        return RecentlyViewProductAddRequestDto.builder()
                .memberUuid(memberUuid)
                .productCode(recentlyViewProductAddRequestVo.getProductCode())
                .productThumbnailUrl(recentlyViewProductAddRequestVo.getProductThumbnailUrl())
                .build();
    }
}
