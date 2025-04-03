package colorful.starbucks.auth.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmailCodeSendResponseVo {

    private String code;


    @Builder
    private EmailCodeSendResponseVo(String code) {
        this.code = code;
    }
}
