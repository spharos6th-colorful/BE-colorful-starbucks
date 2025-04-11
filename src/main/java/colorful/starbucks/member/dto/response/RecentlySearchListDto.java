package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlySearchListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlySearchListDto {

    private LocalDateTime searchAt;
    private String recentlySearch;

    @Builder
    private RecentlySearchListDto(LocalDateTime searchAt, String recentlySearch) {
        this.searchAt = searchAt;
        this.recentlySearch = recentlySearch;
    }

    public static RecentlySearchListDto of(LocalDateTime searchAt, String recentlySearch) {
        return RecentlySearchListDto.builder()
                .searchAt(searchAt)
                .recentlySearch(recentlySearch)
                .build();
    }

    public RecentlySearchListVo toVo(){
        return RecentlySearchListVo.builder()
                .searchAt(searchAt)
                .recentlySearch(recentlySearch)
                .build();
    }
}
