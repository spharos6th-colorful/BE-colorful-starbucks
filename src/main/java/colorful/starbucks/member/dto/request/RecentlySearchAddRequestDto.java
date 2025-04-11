package colorful.starbucks.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecentlySearchAddRequestDto {

    private String memberUuid;
    private String search;

    @Builder
    private RecentlySearchAddRequestDto(String memberUuid, String search) {
        this.memberUuid = memberUuid;
        this.search = search;
    }

    public static RecentlySearchAddRequestDto from(String memberUuid, String search){
        return RecentlySearchAddRequestDto.builder()
                .memberUuid(memberUuid)
                .search(search)
                .build();
    }

}
