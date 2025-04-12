package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.EmailCodeSendRequestVo;
import colorful.starbucks.common.util.EmailAuthCodeGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCodeSendRequestDto {

    private String email;

    @Builder
    private EmailCodeSendRequestDto(String email) {
        this.email = email;
    }
    public String codeGenerator() {
        return EmailAuthCodeGenerator.generateCode(6);
    }
    public static EmailCodeSendRequestDto from(EmailCodeSendRequestVo emailCodeSendRequestVo) {
        return EmailCodeSendRequestDto.builder()
                .email(emailCodeSendRequestVo.getEmail())
                .build();
    }


}
