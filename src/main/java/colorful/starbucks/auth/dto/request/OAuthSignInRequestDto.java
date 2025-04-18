package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.OAuthSignInRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class OAuthSignInRequestDto {

    private String code;

    @Builder
    private OAuthSignInRequestDto(String code) {
        this.code = code;
    }

    public static OAuthSignInRequestDto from(OAuthSignInRequestVo OAuthSignInRequestVo) {
        return OAuthSignInRequestDto.builder()
                .code(OAuthSignInRequestVo.getCode())
                .build();
    }

}

