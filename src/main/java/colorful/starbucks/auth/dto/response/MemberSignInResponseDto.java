package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.vo.response.MemberSignInResponseVo;
import lombok.Builder;

public class MemberSignInResponseDto {

    private String memberUuid;

    private String memberName;

    private String accessToken;

    @Builder

    public MemberSignInResponseDto(String memberUuid,
                                   String memberName,
                                   String accessToken) {
        this.memberUuid = memberUuid;
        this.memberName = memberName;
        this.accessToken = accessToken;
    }

    public static MemberSignInResponseDto from(Member member, String accessToken) {
        return MemberSignInResponseDto.builder()
                .memberUuid(member.getMemberUuid())
                .memberName(member.getMemberName())
                .accessToken(accessToken)
                .build();
    }

    public MemberSignInResponseVo toMemberSignInResponseVo() {
        return MemberSignInResponseVo.builder()
                .accessToken(accessToken)
                .memberUuid(memberUuid)
                .memberName(memberName)
                .build();
    }

}
