package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlySearchListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecentlySearchListDto {

    private LocalDateTime searchAt;
    private String keyword;

    @Builder
    private RecentlySearchListDto(LocalDateTime searchAt, String keyword) {
        this.searchAt = searchAt;
        this.keyword = keyword;
    }

    public static RecentlySearchListDto of(LocalDateTime searchAt, String keyword) {
        return RecentlySearchListDto.builder()
                .searchAt(searchAt)
                .keyword(keyword)
                .build();
    }

    public RecentlySearchListVo toVo(){
        return RecentlySearchListVo.builder()
                .searchAt(searchAt)
                .keyword(keyword)
                .build();
    }
}
