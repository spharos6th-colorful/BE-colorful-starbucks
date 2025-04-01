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
import colorful.starbucks.common.util.TempPasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


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


    @Override
    @Transactional
    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto) {

        if ((memberRepository.existsByEmail(memberSignUpRequestDto.getEmail()))) {
            throw new BaseException(ResponseStatus.DUPLICATED_USER);
        }

        String encodedPassword = passwordEncoder.encode(memberSignUpRequestDto.getPassword());

        String memberUuid = UUID.randomUUID().toString();

        Member member = memberSignUpRequestDto.toEntity(memberUuid, encodedPassword);

        memberRepository.save(member);

    }

    @Override
    public boolean isEmailDuplicated(String email) {

        return memberRepository.existsByEmail(email);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) { //로그인 인증용
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음: " + email));
        return new CustomUserDetails(member);
    }

    public UserDetails loadUserByUuid(String uuid) { // JWT 필터 인증용
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

    private Authentication authenticate(Member member, String inputPassword) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getEmail(), inputPassword)
        );
    }

    @Override
    @Transactional
    public MemberSignInResponseDto signIn(MemberSignInRequestDto signInRequestDto) {

        Member member = memberRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new BaseException(ResponseStatus.FAILED_TO_LOGIN));

        if (member.getSignType() != SignType.NORMAL) {
            throw new BaseException(ResponseStatus.DUPLICATED_SOCIAL_USER);
        }

        try {
            Authentication authentication = authenticate(member, signInRequestDto.getPassword());

            String accessToken = createAccessToken(authentication);
            String refreshToken = createRefreshToken(authentication);

            return MemberSignInResponseDto.from(member, accessToken, refreshToken);

        } catch (Exception e) {
            throw new BaseException(ResponseStatus.FAILED_TO_LOGIN);
        }
    }

    @Override
    @Transactional
    public AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        try {
            String uuid = jwtTokenProvider.validateAndExtractUuid(refreshTokenRequestDto.getRefreshToken());
            UserDetails userDetails = loadUserByUuid(uuid);
            String newAccessToken = jwtTokenProvider.generateAccessToken(
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities())
            );

            return AccessTokenResponseDto.builder()
                    .accessToken(newAccessToken)
                    .build();
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.TOKEN_NOT_VALID);
        }
    }

    @Override
    @Transactional
    public MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto memberEmailFindRequestDto) {
        Member member = memberRepository.findByMemberNameAndPhoneNumber(
                memberEmailFindRequestDto.getMemberName(),
                memberEmailFindRequestDto.getPhoneNumber()
        ).orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER));
        return MemberEmailFindResponseDto.from(member.getEmail());

    }

    @Override
    @Transactional
    public MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto dto) {
        try {
            if (!memberRepository.existsByEmail(dto.getEmail())) {
                throw new BaseException(ResponseStatus.NO_EXIST_USER);
            }

            Member member = memberRepository.findByEmailAndMemberNameAndPhoneNumber(
                    dto.getEmail().trim(),
                    dto.getMemberName().trim(),
                    dto.getPhoneNumber().trim()
            ).orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_USER)
            );

            String tempPassword = TempPasswordGenerator.generate(8);
            String encodedPassword = passwordEncoder.encode(tempPassword);
            member.updatePassword(encodedPassword);
            emailService.sendTempPassword(member.getEmail(), tempPassword);

            return MemberPasswordResetResponseDto.fromMessage("임시 비밀번호가 이메일로 전송 되었습니다.");

        } catch (BaseException e) {
            e.printStackTrace();
            throw new BaseException(ResponseStatus.INTERNAL_SERVER_ERROR, "비밀번호 초기화 중 오류가 발생했습니다.");
        }
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

            return MemberSignInResponseDto.from(member, jwtAccessToken, jwtRefreshToken);

        } catch (BaseException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException(ResponseStatus.KAKAO_USER_INFO_ERROR, "카카오 로그인 처리 중 오류 발생");
        }
    }


}



