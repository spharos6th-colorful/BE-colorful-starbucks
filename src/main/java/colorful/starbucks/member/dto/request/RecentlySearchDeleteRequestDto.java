package colorful.starbucks.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlySearchDeleteRequestDto {

    private String memberUuid;
    private String keyword;

    @Builder
    private RecentlySearchDeleteRequestDto(String memberUuid, String keyword) {
        this.memberUuid = memberUuid;
        this.keyword = keyword;
    }

    public static RecentlySearchDeleteRequestDto of(String memberUuid, String keyword){
        return RecentlySearchDeleteRequestDto.builder()
                .memberUuid(memberUuid)
                .keyword(keyword)
                .build();
    }
}
