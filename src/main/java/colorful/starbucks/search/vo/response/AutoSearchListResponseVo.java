package colorful.starbucks.search.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AutoSearchListResponseVo {

    private List<AutoSearchResponseVo> autoSearchList;

    @Builder
    public AutoSearchListResponseVo(List<AutoSearchResponseVo> autoSearchList) {
        this.autoSearchList = autoSearchList;
    }
}
