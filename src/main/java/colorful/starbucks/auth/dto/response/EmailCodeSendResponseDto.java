package colorful.starbucks.auth.dto.response;

import colorful.starbucks.auth.vo.response.EmailCodeSendResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCodeSendResponseDto {

    private String code;

    @Builder
    private EmailCodeSendResponseDto(String code) {
        this.code = code;
    }


    public static EmailCodeSendResponseDto from(String code) {
        return new EmailCodeSendResponseDto(code);
    }

    public EmailCodeSendResponseVo toVo() {
        return EmailCodeSendResponseVo.builder()
                .code(this.code)
                .build();
    }


}
