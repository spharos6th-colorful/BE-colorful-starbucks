package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlyViewProductListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlyViewProductListResponseDto {

    private List<RecentlyViewProductListDto> productCodesPerDate;

    @Builder
    private RecentlyViewProductListResponseDto(List<RecentlyViewProductListDto> productCodesPerDate) {
        this.productCodesPerDate = productCodesPerDate;
    }

    public static RecentlyViewProductListResponseDto from(List<RecentlyViewProductListDto> productCodesPerDate) {
        return RecentlyViewProductListResponseDto.builder()
                .productCodesPerDate(productCodesPerDate)
                .build();
    }

    public RecentlyViewProductListResponseVo toVo() {
        return RecentlyViewProductListResponseVo.builder()
                .productCodesPerDate(productCodesPerDate)
                .build();
    }
}
