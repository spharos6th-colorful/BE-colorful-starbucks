package colorful.starbucks.member.dto.response;

import colorful.starbucks.member.vo.response.RecentlySearchListResponseVo;
import colorful.starbucks.member.vo.response.RecentlyViewProductListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecentlySearchListResponseDto {

    private List<RecentlySearchListDto> recentlySearchKeywords;

    @Builder
    private RecentlySearchListResponseDto(List<RecentlySearchListDto> recentlySearchKeywords) {
        this.recentlySearchKeywords = recentlySearchKeywords;
    }

    public static RecentlySearchListResponseDto from(List<RecentlySearchListDto> recentlySearchKeywords) {
        return RecentlySearchListResponseDto.builder()
                .recentlySearchKeywords(recentlySearchKeywords)
                .build();
    }

    public RecentlySearchListResponseVo toVo() {
        return RecentlySearchListResponseVo.builder()
                .recentlySearchKeywords(
                        recentlySearchKeywords.stream()
                                .map(RecentlySearchListDto::toVo)
                                .toList()
                )
                .build();
    }
}
