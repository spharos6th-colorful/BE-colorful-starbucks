package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlySearchListResponseVo {

    private List<RecentlySearchListVo> recentlySearch;

    @Builder
    private RecentlySearchListResponseVo(List<RecentlySearchListVo> recentlySearch) {
        this.recentlySearch = recentlySearch;
    }
}
