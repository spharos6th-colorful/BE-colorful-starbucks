package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.domain.MemberLevel;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.*;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.service.EmailService;
import colorful.starbucks.common.util.UuidGenerator;
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

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final KakaoApiService kakaoApiService;
    private final UserDetailsService userDetailsService;
    private final EmailAuthRedisService emailAuthRedisService;
    private final RefreshTokenRedisService refreshTokenRedisService;


    @Override
    public UserDetails loadUserByUuid(String uuid) {
        Member member = memberRepository.findByMemberUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("UUID 사용자 없음: " + uuid));
        return new CustomUserDetails(member);
    }


    @Transactional
    @Override
    public MemberSignInResponseDto signIn(MemberSignInRequestDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                toAuthenticationToken(dto)
        );
        return generateTokensAndSaveRefresh(authentication);
    }

    @Transactional
    @Override
    public MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto dto) {
        KakaoUserInfo userInfo = dto.fetchUserInfo(kakaoApiService);

        if (userInfo.getEmail() == null || userInfo.getEmail().isBlank()) {
            throw new BaseException(ResponseStatus.INVALID_EMAIL_ADDRESS);
        }

        Member member = findOrCreateKakaoMember(userInfo);
        UserDetails userDetails = new CustomUserDetails(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        return generateTokensAndSaveRefresh(authentication);
    }

    private MemberSignInResponseDto generateTokensAndSaveRefresh(Authentication authentication) {
        String accessToken = createAccessToken(authentication);
        String refreshToken = createRefreshToken(authentication);

        String uuid = ((CustomUserDetails) authentication.getPrincipal()).getMemberUuid();
        refreshTokenRedisService.saveRefreshToken(
                uuid,
                refreshToken,
                jwtTokenProvider.getRefreshTokenExpireTime()
        );

        return MemberSignInResponseDto.from(accessToken, refreshToken);
    }

    private UsernamePasswordAuthenticationToken toAuthenticationToken(MemberSignInRequestDto dto) {
        return new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
    }


    @Transactional
    @Override
    public void signUp(MemberSignUpRequestDto dto) {
        memberRepository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new BaseException(ResponseStatus.DUPLICATED_USER)
        );
        memberRepository.save(dto.toEntityWithEncodePassword(passwordEncoder));
    }

    @Override
    public boolean isEmailDuplicated(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional
    @Override
    public AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto dto) {
        Authentication authentication = getAuthenticationFromRefreshToken(dto.getRefreshToken());
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);
        return AccessTokenResponseDto.from(newAccessToken);
    }

    private Authentication getAuthenticationFromRefreshToken(String refreshToken) {
        String uuid = jwtTokenProvider.validateAndExtractUuid(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(uuid);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    private String createAccessToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }

    private String createRefreshToken(Authentication authentication) {
        return jwtTokenProvider.generateRefreshToken(authentication);
    }

    @Transactional
    @Override
    public EmailCodeSendResponseDto sendEmail(EmailCodeSendRequestDto dto) {
        String code = dto.codeGenerator();
        emailAuthRedisService.saveCode(dto.getEmail(), code);
        emailService.sendEmailCode(dto.getEmail(), code);
        return EmailCodeSendResponseDto.from(code);
    }

    @Transactional
    @Override
    public void verifyEmailCode(EmailVerifyCodeRequestDto dto) {
        if (emailAuthRedisService.verifyCode(dto.getEmail(), dto.getCode())) {
            emailAuthRedisService.deleteCode(dto.getEmail());
        } else {
            throw new BaseException(ResponseStatus.INVALID_AUTH_CODE);
        }
    }

    @Transactional
    @Override
    public MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto dto) {
        if (!memberRepository.existsByEmail(dto.getEmail())) {
            throw new BaseException(ResponseStatus.NO_EXIST_USER);
        }

        Member member = dto.findMatchingMember(memberRepository)
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));

        dto.generateTempPassword(passwordEncoder);
        member.updatePassword(dto.getEncodedPassword());
        emailService.sendTempPassword(member.getEmail(), dto.getTempPassword());

        return MemberPasswordResetResponseDto.from("임시 비밀번호가 이메일로 전송 되었습니다.");
    }


    private Member findOrCreateKakaoMember(KakaoUserInfo kakaoUserInfo) {
        return memberRepository.findBySignTypeAndSocialId(SignType.KAKAO, kakaoUserInfo.getId())
                .orElseGet(() -> memberRepository.save(
                        Member.builder()
                                .signType(SignType.KAKAO)
                                .socialId(kakaoUserInfo.getId())
                                .email(kakaoUserInfo.getEmail())
                                .memberUuid(UuidGenerator.generateUuid())
                                .memberLevel(MemberLevel.WHITE)
                                .build()
                ));
    }

    @Transactional
    @Override
    public MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto dto) {
        Member member = dto.findByMemberNameAndPhoneNumber(memberRepository)
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));
        return MemberEmailFindResponseDto.from(member.getEmail());
    }

    @Transactional
    @Override
    public void signOut(MemberSignOutRequestDto memberSignOutRequeestDto) {
        String uuid = jwtTokenProvider.validateAndExtractUuid(memberSignOutRequeestDto.getRefreshToken());
        refreshTokenRedisService.deleteRefreshToken(uuid);
    }


}