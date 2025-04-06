package colorful.starbucks.order.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.order.dto.PreOrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class OrderRedisService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private static final String PREFIX = "order:";

    public void saveOrder(String orderId, PreOrderDto preOrderDto, long timeoutSeconds) {
        String json = preOrderDto.toJson(objectMapper);
        redisTemplate.opsForValue().set(PREFIX + orderId, json, timeoutSeconds, TimeUnit.SECONDS);
    }

    public PreOrderDto getOrder(String orderId) {
        String json = redisTemplate.opsForValue().get(PREFIX + orderId);
        if (json == null) return null;
        return PreOrderDto.fromJson(json, objectMapper);
    }

    public void deleteOrder(String orderId) {
        redisTemplate.delete(PREFIX + orderId);
    }

    public boolean orderExists(String orderId) {
        return redisTemplate.hasKey(PREFIX + orderId);
    }

    public void extendOrderTtl(String orderId, long timeoutSeconds) {
        Boolean extended = redisTemplate.expire(PREFIX + orderId, timeoutSeconds, TimeUnit.SECONDS);
        if(Boolean.FALSE.equals(extended)) {
            throw new BaseException(ResponseStatus.REDIS_TTL_EXTEND_FAIL);
        }
    }
}
