package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberEmailFindResponseVo {

    private String email;

    @Builder
    private MemberEmailFindResponseVo(String email) {
        this.email = email;
    }
}
