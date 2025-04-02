package colorful.starbucks.auth.vo.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailCodeSendRequestVo {

    private String email;

    @Builder
    private EmailCodeSendRequestVo(String email) {
        this.email = email;
    }
}
