package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.vo.request.KakaoSignInRequestVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoSignInRequestDto {

    private  String code;

    @Builder
    private KakaoSignInRequestDto(String code) {
        this.code = code;
    }

    public static KakaoSignInRequestDto from(KakaoSignInRequestVo kakaoSignInRequestVo){
        return KakaoSignInRequestDto.builder()
                .code(kakaoSignInRequestVo.getCode())
                .build();
    }
}
