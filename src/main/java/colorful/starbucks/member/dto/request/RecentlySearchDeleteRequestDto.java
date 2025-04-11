package colorful.starbucks.member.dto.request;

import lombok.Getter;

@Getter
public class RecentlySearchDeleteRequestDto {

    private String memberUuid;
    private String keyword;
}
