package colorful.starbucks.member.vo.response;

import colorful.starbucks.member.dto.response.RecentlyViewProductListDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlyViewProductListResponseVo {

    private List<RecentlyViewProductListDto> productCodesPerDate;

    @Builder
    private RecentlyViewProductListResponseVo(List<RecentlyViewProductListDto> productCodesPerDate) {
        this.productCodesPerDate = productCodesPerDate;
    }
}
