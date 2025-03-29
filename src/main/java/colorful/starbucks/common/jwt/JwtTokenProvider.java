package colorful.starbucks.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

    private final Environment env;

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateAccessToken(Authentication authentication) {
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("JWT.token.access-expire-time", Long.class));

        return Jwts.builder()
                .signWith(getSignKey())
                .claim("uuid", claims.getSubject())
                .issuedAt(now)
                .expiration(expiration)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().subject(authentication.getName()).build();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + env.getProperty("JWT.token.refresh-expire-time", Long.class));

        return Jwts.builder()
                .signWith(getSignKey())
                .claim("uuid", claims.getSubject())
                .issuedAt(now)
                .expiration(expiration)
                .compact();
    }

    public Key getSignKey() {
        return Keys.hmacShaKeyFor(env.getProperty("JWT.secret-key").getBytes());
    }

    public String validateAndExtractUuid(String token) {
        try {
            return extractAllClaims(token).get("uuid", String.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 RefreshToken 입니다.");
        }
    }
}

