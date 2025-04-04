package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignInResponseDto {

    private String accessToken;

    private String refreshToken;

    @Builder
    public MemberSignInResponseDto(String accessToken,
                                   String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static MemberSignInResponseDto from(String accessToken, String refreshToken) {
        return MemberSignInResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public MemberSignInResponseVo toVo() {
        return MemberSignInResponseVo.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
