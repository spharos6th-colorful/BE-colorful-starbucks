package colorful.starbucks.auth.application;

import colorful.starbucks.auth.domain.CustomUserDetails;
import colorful.starbucks.auth.domain.OAuth;
import colorful.starbucks.auth.dto.request.MemberSignInRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignOutRequestDto;
import colorful.starbucks.auth.dto.request.MemberSignUpRequestDto;
import colorful.starbucks.auth.dto.request.RefreshTokenRequestDto;
import colorful.starbucks.auth.dto.response.AccessTokenResponseDto;
import colorful.starbucks.auth.dto.response.MemberSignInResponseDto;
import colorful.starbucks.auth.infrastructure.AuthRepository;
import colorful.starbucks.auth.infrastructure.OAuthRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.jwt.JwtTokenProvider;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.TokenGenerator;
import colorful.starbucks.member.domain.Member;
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
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final OAuthRepository oAuthRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final RefreshTokenRedisService refreshTokenRedisService;
    private final TokenGenerator tokenGenerator;


    @Override
    public UserDetails loadUserByUuid(String uuid) {

        Member member = authRepository.findByMemberUuid(uuid).orElse(null);

        OAuth oAuth = oAuthRepository.findByMemberUuid(uuid).orElse(null);

        if (member != null) {
            return new CustomUserDetails(member);
        } else if (oAuth != null) {
            return new CustomUserDetails(oAuth);
        } else {
            throw new UsernameNotFoundException("UUID 사용자 없음: " + uuid);
        }
    }


    @Transactional
    @Override
    public MemberSignInResponseDto signIn(MemberSignInRequestDto memberSignInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                toAuthenticationToken(memberSignInRequestDto)
        );
        return tokenGenerator.issueTokens(authentication);
    }


    private UsernamePasswordAuthenticationToken toAuthenticationToken(MemberSignInRequestDto memberSignInRequestDto) {
        return new UsernamePasswordAuthenticationToken(memberSignInRequestDto.getEmail(), memberSignInRequestDto.getPassword());
    }


    @Transactional
    @Override
    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto) {
        authRepository.findByEmail(memberSignUpRequestDto.getEmail()).ifPresent(
                (member) -> {
                    throw new BaseException(ResponseStatus.DUPLICATED_USER);
                }
        );
        authRepository.save(memberSignUpRequestDto.toEntityWithEncodePassword(passwordEncoder));
    }

    @Transactional
    @Override
    public AccessTokenResponseDto reIssueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        Authentication authentication = getAuthenticationFromRefreshToken(refreshTokenRequestDto.getRefreshToken());
        String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);
        return AccessTokenResponseDto.from(newAccessToken);
    }

    private Authentication getAuthenticationFromRefreshToken(String refreshToken) {
        String uuid = jwtTokenProvider.validateAndExtractUuid(refreshToken);
        UserDetails userDetails = userDetailsService.loadUserByUsername(uuid);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


    @Transactional
    @Override
    public void signOut(MemberSignOutRequestDto memberSignOutRequeestDto) {
        String uuid = jwtTokenProvider.validateAndExtractUuid(memberSignOutRequeestDto.getRefreshToken());
        refreshTokenRedisService.deleteRefreshToken(uuid);
    }


}