package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.application.KakaoApiService;
import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.response.KakaoUserInfo;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.auth.vo.request.KakaoSignInRequestVo;
import colorful.starbucks.common.util.UuidGenerator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoSignInRequestDto {

    private final String code;

    @Builder
    public KakaoSignInRequestDto(String code) {
        this.code = code;
    }

    public static KakaoSignInRequestDto from(KakaoSignInRequestVo kakaoSignInRequestVo) {
        return KakaoSignInRequestDto.builder()
                .code(kakaoSignInRequestVo.getCode())
                .build();
    }

    public KakaoUserInfo fetchUserInfo(KakaoApiService kakaoApiService) {
        return kakaoApiService.getUserInfo(kakaoApiService.getAccessToken(code));
    }


}

