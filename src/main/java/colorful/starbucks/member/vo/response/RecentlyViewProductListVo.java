package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlyViewProductListVo {

    private LocalDate viewedAt;
    private List<RecentlyViewProductResponseVo> recentlyViewProducts;

    @Builder
    private RecentlyViewProductListVo(LocalDate viewedAt, List<RecentlyViewProductResponseVo> recentlyViewProducts) {
        this.viewedAt = viewedAt;
        this.recentlyViewProducts = recentlyViewProducts;
    }
}
