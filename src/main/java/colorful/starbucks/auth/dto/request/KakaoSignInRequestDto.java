package colorful.starbucks.auth.dto.request;

import colorful.starbucks.auth.application.KakaoApiService;
import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.response.KakaoUserInfo;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.auth.vo.request.KakaoSignInRequestVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class KakaoSignInRequestDto {

    private final String code;

    @Builder
    public KakaoSignInRequestDto(String code) {
        this.code = code;
    }

    public static KakaoSignInRequestDto from(KakaoSignInRequestVo vo) {
        return KakaoSignInRequestDto.builder()
                .code(vo.getCode())
                .build();
    }

    public KakaoUserInfo fetchUserInfo(KakaoApiService kakaoApiService) {
        return kakaoApiService.getUserInfo(kakaoApiService.getAccessToken(code));
    }

    public Member findOrCreateKakaoMember(KakaoUserInfo userInfo, MemberRepository repository) {
        return repository.findBySignTypeAndSocialId(SignType.KAKAO, userInfo.getId())
                .orElseGet(() -> repository.save(
                        Member.builder()
                                .signType(SignType.KAKAO)
                                .socialId(userInfo.getId())
                                .email(userInfo.getEmail())
                                .memberUuid(UUID.randomUUID().toString())
                                .build()
                ));
    }
}

