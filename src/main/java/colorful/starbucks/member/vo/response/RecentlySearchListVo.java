package colorful.starbucks.member.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecentlySearchListVo {

    private LocalDateTime searchAt;
    private String recentlySearch;

    @Builder
    private RecentlySearchListVo(LocalDateTime searchAt, String recentlySearch) {
        this.searchAt = searchAt;
        this.recentlySearch = recentlySearch;
    }
}
