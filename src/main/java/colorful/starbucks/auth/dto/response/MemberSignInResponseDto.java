package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import lombok.Builder;

public class MemberSignInResponseDto {

    private String accessToken;

    @Builder

    public MemberSignInResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public static MemberSignInResponseDto from(Member member, String accessToken) {
        return MemberSignInResponseDto.builder()
                .accessToken(accessToken)
                .build();
    }

    public MemberSignInResponseVo toMemberSignInResponseVo() {
        return MemberSignInResponseVo.builder()
                .accessToken(accessToken)
                .build();
    }

}
