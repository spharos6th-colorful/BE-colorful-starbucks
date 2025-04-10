package colorful.starbucks.auth.domain;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.member.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class CustomUserDetails implements UserDetails {

    private final Member member;
    private final OAuth oAuth;

    public CustomUserDetails(Member member) {
        this.member = member;
        this.oAuth = null;
    }

    public CustomUserDetails(OAuth oAuth) {
        this.member = null;
        this.oAuth = oAuth;
    }

    @Override
    public String getUsername() {
        if (member != null) {
            return member.getMemberUuid();
        } else if (oAuth != null) {
            return oAuth.getMemberUuid();
        }
        throw new BaseException(ResponseStatus.MEMBER_OR_OAUTH_REQUIRED,"회원 또는 OAuth 정보가 존재하지 않습니다.");
    }

    @Override
    public String getPassword() {
        if (member != null) {
            return member.getPassword();
        }
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getMemberUuid() {
        if (member != null) {
            return member.getMemberUuid();
        } else if (oAuth != null) {
            return oAuth.getMemberUuid();
        }
        throw new BaseException(ResponseStatus.AUTHENTICATION_TARGET_NOT_FOUND,"사용자를 찾을 수 없습니다.");
    }
}

