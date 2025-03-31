package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.domain.SignType;
import colorful.starbucks.auth.dto.request.*;
import colorful.starbucks.auth.dto.response.*;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.security.CustomUserDetails;
import colorful.starbucks.common.service.EmailService;
import colorful.starbucks.common.util.TempPasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패"));

        if(member.getSignType() != SignType.NORMAL){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"소셜 로그인 계정입니다. ");
        }

        try {
            Authentication authentication = authenticate(member, signInRequestDto.getPassword());

            String accessToken = createAccessToken(authentication);
            String refreshToken = createRefreshToken(authentication);

            return MemberSignInResponseDto.from(member, accessToken, refreshToken);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }
    }

    @Override
    @Transactional
    public AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto){
        String uuid = jwtTokenProvider.validateAndExtractUuid(refreshTokenRequestDto.getRefreshToken());
        UserDetails userDetails = loadUserByUuid(uuid);
        String newAccessToken = jwtTokenProvider.generateAccessToken(
                new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities())
        );

        return AccessTokenResponseDto.builder()
                .accessToken(newAccessToken)
                .build();
    }

    @Override
    @Transactional
    public MemberEmailFindResponseDto findEmail(MemberEmailFindRequestDto memberEmailFindRequestDto){
        Member member = memberRepository.findByMemberNameAndPhoneNumber(
                memberEmailFindRequestDto.getMemberName(),
                memberEmailFindRequestDto.getPhoneNumber()
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"입력하신 정보와 일치하는 회원이 없습니다.")
        );
        return MemberEmailFindResponseDto.from(member.getEmail());

    }

    @Override
    @Transactional
    public MemberPasswordResetResponseDto findPassword(MemberPasswordResetRequestDto dto){
        try {
            Optional<Member> optionalMember = memberRepository.findByEmail(dto.getEmail());

            Member member = memberRepository.findByEmailAndMemberNameAndPhoneNumber(
                    dto.getEmail().trim(),
                    dto.getMemberName().trim(),
                    dto.getPhoneNumber().trim()
            ).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"입력하신 정보와 일치하는 회원이 없습니다.")
            );

            String tempPassword = TempPasswordGenerator.generate(8);
            String encodedPassword = passwordEncoder.encode(tempPassword);
            member.updatePassword(encodedPassword);
            emailService.sendTempPassword(member.getEmail(), tempPassword);

            return MemberPasswordResetResponseDto.fromMessage("임시 비밀번호가 이메일로 전송 되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
    @Override
    @Transactional
    public MemberSignInResponseDto kakaoSignIn(KakaoSignInRequestDto kakaoSignInRequestDto) {

        String accessToken = kakaoApiService.getAccessToken(kakaoSignInRequestDto.getCode());


        KakaoUserInfo userInfo = kakaoApiService.getUserInfo(accessToken);


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
    }




}



