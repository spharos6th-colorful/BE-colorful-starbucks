package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecentlySearchListVo {

    private LocalDateTime searchAt;
    private String keyword;

    @Builder
    private RecentlySearchListVo(LocalDateTime searchAt, String keyword) {
        this.searchAt = searchAt;
        this.keyword = keyword;
    }
}
