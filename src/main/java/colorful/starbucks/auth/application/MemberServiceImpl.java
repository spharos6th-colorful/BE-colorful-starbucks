package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.dto.request.MemberSignInRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.security.CustomUserDetails;
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

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


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

        try {
            Authentication authentication = authenticate(member, signInRequestDto.getPassword());

            String accessToken = createAccessToken(authentication);
            String refreshToken = createRefreshToken(authentication);

            return MemberSignInResponseDto.from(member, accessToken, refreshToken);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 실패");
        }
    }
}


