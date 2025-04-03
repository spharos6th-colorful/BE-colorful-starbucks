package colorful.starbucks.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService {

    private final StringRedisTemplate redisTemplate;
    private static final String PREFIX = "RT:";

    public void saveRefreshToken(String memberUuid, String refreshToken, long expirationMillis) {
        redisTemplate.opsForValue().set(
                key(memberUuid),
                refreshToken,
                expirationMillis,
                TimeUnit.MILLISECONDS
        );
    }

    public String getRefreshToken(String memberUuid) {
        return redisTemplate.opsForValue().get(key(memberUuid));
    }

    public void deleteRefreshToken(String memberUuid) {
        redisTemplate.delete(key(memberUuid));
    }

    public boolean verifyRefreshToken(String memberUuid, String refreshToken) {
        String stored = getRefreshToken(memberUuid);
        return refreshToken.equals(stored);
    }

    private String key(String memberUuid) {
        return PREFIX + memberUuid;
    }
}
