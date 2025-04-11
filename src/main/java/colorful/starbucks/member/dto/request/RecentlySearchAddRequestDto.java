package colorful.starbucks.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlySearchAddRequestDto {

    private String memberUuid;
    private String keyword;

    @Builder
    private RecentlySearchAddRequestDto(String memberUuid, String keyword) {
        this.memberUuid = memberUuid;
        this.keyword = keyword;
    }

    public static RecentlySearchAddRequestDto of(String memberUuid, String keyword){
        return RecentlySearchAddRequestDto.builder()
                .memberUuid(memberUuid)
                .keyword(keyword)
                .build();
    }

}
