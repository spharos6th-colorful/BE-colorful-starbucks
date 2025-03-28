package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberSignInResponseVo {

    private String memberUuid;

    private String accessToken;

    private String memberName;

    @Builder
    public MemberSignInResponseVo(String memberUuid,
                                  String accessToken,
                                  String memberName) {
        this.memberUuid = memberUuid;
        this.accessToken = accessToken;
        this.memberName = memberName;
    }
}
