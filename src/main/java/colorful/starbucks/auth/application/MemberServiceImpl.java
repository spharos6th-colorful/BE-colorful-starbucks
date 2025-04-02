package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.*;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.response.ResponseStatus;
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
        Authentication authentication = authenticationManager.authenticate(signInRequestDto.toAuthenticationToken());

        String accessToken = createAccessToken(authentication);
        String refreshToken = createRefreshToken(authentication);

        return MemberSignInResponseDto.from(accessToken, refreshToken);
    }

    @Override
    @Transactional
    public AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto dto) {
        Authentication authentication = dto.toAuthentication(jwtTokenProvider, userDetailsService);
        return AccessTokenResponseDto.from(jwtTokenProvider.generateAccessToken(authentication));
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

        return MemberPasswordResetResponseDto.from("임시 비밀번호가 이메일로 전송 되었습니다.");
    }

    @Override
    @Transactional
    public MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto dto) {
        KakaoUserInfo userInfo = dto.fetchUserInfo(kakaoApiService);

        if (userInfo.getEmail() == null || userInfo.getEmail().isBlank()) {
            throw new BaseException(ResponseStatus.INVALID_EMAIL_ADDRESS);
        }

        Member member = dto.findOrCreateKakaoMember(userInfo, memberRepository);

        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getMemberUuid(), null, null);

        return MemberSignInResponseDto.from(
                jwtTokenProvider.generateAccessToken(authentication),
                jwtTokenProvider.generateRefreshToken(authentication)
        );
    }

    @Override
    @Transactional
    public EmailCodeSendResponseDto sendEmail(EmailCodeSendRequestDto emailCodeSendRequestDto) {
        String code = emailCodeSendRequestDto.codeGenerator();
        emailAuthRedisService.saveCode(emailCodeSendRequestDto.getEmail(), code);
        emailService.sendEmailCode(emailCodeSendRequestDto.getEmail(), code);

        return EmailCodeSendResponseDto.from(code);
    }

    @Override
    @Transactional
    public void verifyEmailCode(EmailVerifyCodeRequestDto dto) {
        if (!emailAuthRedisService.verifyCode(dto.getEmail(), dto.getCode())) {
            throw new BaseException(ResponseStatus.INVALID_AUTH_CODE);
        }
        emailAuthRedisService.deleteCode(dto.getEmail());
    }



}