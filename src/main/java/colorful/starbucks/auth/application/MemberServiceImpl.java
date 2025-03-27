package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.infrastructure.MemberRepository;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.security.CustomUserDetails;
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


    @Override
    @Transactional
    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto){

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
    public UserDetails loadUserByUsername(String memberUuid) {
        Member member = memberRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음: " + memberUuid));
        return new CustomUserDetails(member);
    }
    private String createToken(Authentication authentication) {
        return jwtTokenProvider.generateAccessToken(authentication);
    }
    private Authentication authenticate(Member member, String inputPassword) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getEmail(), inputPassword)
        );
    }

}
