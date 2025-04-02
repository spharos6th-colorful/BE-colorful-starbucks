package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.*;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.common.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static colorful.starbucks.auth.domain.QMember.member;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final KakaoApiService kakaoApiService;
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto) {

        memberRepository.findByEmail(memberSignUpRequestDto.getEmail()).orElseThrow(
                () -> new BaseException(ResponseStatus.DUPLICATED_USER)
        );
        memberRepository.save(memberSignUpRequestDto.toEntityWithEncodePassword(passwordEncoder));
    }

    @Override
    public boolean isEmailDuplicated(String email) {

        return memberRepository.existsByEmail(email);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUuid(String uuid) {
        Member member = memberRepository.findByMemberUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("UUID 사용자 없음: " + uuid));
        return new CustomUserDetails(member);
    }

    private String createAccessToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }

    private String createRefreshToken(Authentication authentication) {
        return jwtTokenProvider.generateRefreshToken(authentication);
    }

    @Override
    @Transactional
    public MemberSignInResponseDto signIn(MemberSignInRequestDto signInRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(signInRequestDto.toAuthenticationToken());

            String accessToken = createAccessToken(authentication);
            String refreshToken = createRefreshToken(authentication);

            return MemberSignInResponseDto.from(accessToken, refreshToken);

        } catch (Exception e) {
            throw new BaseException(ResponseStatus.FAILED_TO_LOGIN);
        }
    }

    @Override
    @Transactional
    public AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        try {
            Authentication authentication = refreshTokenRequestDto.toAuthentication(jwtTokenProvider, userDetailsService);
            String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);

            return AccessTokenResponseDto.from(newAccessToken);
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.TOKEN_NOT_VALID);
        }
    }

    @Override
    @Transactional
    public MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto dto) {
        Member member = dto.findByMemberNameAndPhoneNumber(memberRepository)
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));
        return MemberEmailFindResponseDto.from(member.getEmail());
    }

    @Override
    @Transactional
    public MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto dto) {

        if (!memberRepository.existsByEmail(dto.getEmail())) {
            throw new BaseException(ResponseStatus.NO_EXIST_USER);
        }

        Member member = dto.findMatchingMember(memberRepository)
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));

        dto.generateTempPassword(passwordEncoder);
        member.updatePassword(dto.getEncodedPassword());
        emailService.sendTempPassword(member.getEmail(), dto.getTempPassword());

        return MemberPasswordResetResponseDto.fromMessage("임시 비밀번호가 이메일로 전송 되었습니다.");
    }


    @Override
    @Transactional
    public MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto kakaoSignInRequestDto) {
        try {
            String accessToken = kakaoApiService.getAccessToken(kakaoSignInRequestDto.getCode());
            if (accessToken == null || accessToken.isEmpty()) {
                throw new BaseException(ResponseStatus.KAKAO_TOKEN_ERROR);
            }

            KakaoUserInfo userInfo = kakaoApiService.getUserInfo(accessToken);
            if (userInfo.getEmail() == null || userInfo.getEmail().isBlank()) {
                throw new BaseException(ResponseStatus.INVALID_EMAIL_ADDRESS);
            }

            Member member = memberRepository.findBySignTypeAndSocialId(SignType.KAKAO, userInfo.getId())
                    .orElseGet(() -> memberRepository.save(
                            Member.builder()
                                    .signType(SignType.KAKAO)
                                    .socialId(userInfo.getId())
                                    .email(userInfo.getEmail())
                                    .memberUuid(UUID.randomUUID().toString())
                                    .build()
                    ));

            Authentication authentication = new UsernamePasswordAuthenticationToken(member.getMemberUuid(), null, null);
            String jwtAccessToken = jwtTokenProvider.generateAccessToken(authentication);
            String jwtRefreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            return MemberSignInResponseDto.from(jwtAccessToken, jwtRefreshToken);

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(ResponseStatus.KAKAO_USER_INFO_ERROR, "카카오 로그인 처리 중 오류 발생");
        }
    }
}