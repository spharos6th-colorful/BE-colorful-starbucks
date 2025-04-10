package colorful.starbucks.auth.domain;

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
        throw new IllegalStateException("Neither Member nor OAuth is set");
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
        throw new IllegalStateException("Neither Member nor OAuth is set");
    }
}

