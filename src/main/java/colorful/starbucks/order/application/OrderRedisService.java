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


    public void saveOrder(Long orderCode, PreOrderDto preOrderDto, long timeoutSeconds) {
        String json = preOrderDto.toJson(objectMapper);
        redisTemplate.opsForValue().set(PREFIX + orderCode, json, timeoutSeconds, TimeUnit.SECONDS);
    }


    public PreOrderDto getOrder(Long orderCode) {
        String json = redisTemplate.opsForValue().get(PREFIX + orderCode);
        if (json == null) return null;
        return PreOrderDto.fromJson(json, objectMapper);
    }


    public void deleteOrder(Long orderCode) {
        redisTemplate.delete(PREFIX + orderCode);
    }


    public boolean orderExists(Long orderCode) {
        return redisTemplate.hasKey(PREFIX + orderCode);
    }


    public void extendOrderTtl(Long orderCode, long timeoutSeconds) {
        Boolean extended = redisTemplate.expire(PREFIX + orderCode, timeoutSeconds, TimeUnit.SECONDS);
        if (Boolean.FALSE.equals(extended)) {
            throw new BaseException(ResponseStatus.REDIS_TTL_EXTEND_FAIL);
        }
    }


    public void validateOrderForPayment(Long orderCode, Integer paidAmount) {
        PreOrderDto preOrderDto = getOrder(orderCode);

        if (preOrderDto == null) {
            throw new BaseException(ResponseStatus.NO_EXIST_ORDER); // 주문 자체가 없음
        }

        if (!preOrderDto.getTotalAmount().equals(paidAmount)) {
            throw new BaseException(ResponseStatus.NO_EXIST_ORDER); // 금액 불일치
        }
    }
}
