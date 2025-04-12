package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlySearchListResponseVo {

    private List<RecentlySearchListVo> recentlySearchKeywords;

    @Builder
    private RecentlySearchListResponseVo(List<RecentlySearchListVo> recentlySearchKeywords) {
        this.recentlySearchKeywords = recentlySearchKeywords;
    }
}
