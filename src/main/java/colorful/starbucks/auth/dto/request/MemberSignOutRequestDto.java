package colorful.starbucks.auth.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignOutRequestDto {

    private String refreshToken;

    @Builder
    private MemberSignOutRequestDto(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static MemberSignOutRequestDto from(MemberSignOutRequestDto memberSignOutRequeestDto) {
        return MemberSignOutRequestDto.builder()
                .refreshToken(memberSignOutRequeestDto.getRefreshToken())
                .build();
    }
}
