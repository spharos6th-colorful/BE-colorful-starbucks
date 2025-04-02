package colorful.starbucks.auth.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class EmailAuthRedisService {

    private final StringRedisTemplate redisTemplate;
    private static final String PREFIX = "email-auth:";


    public void saveCode(String email, String code) {
        redisTemplate.opsForValue().set(PREFIX + email, code, 3, TimeUnit.MINUTES);
    }


    public boolean verifyCode(String email, String inputCode) {
        String stored = redisTemplate.opsForValue().get(PREFIX + email);
        return inputCode.equals(stored);
    }


    public void deleteCode(String email) {
        redisTemplate.delete(PREFIX + email);
    }
}

