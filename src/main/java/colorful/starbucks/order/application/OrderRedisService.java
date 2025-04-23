package colorful.starbucks.order.application;

import colorful.starbucks.order.dto.PreOrderDto;
import colorful.starbucks.order.dto.request.PreOrderRequestDto;
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
    private static final long DEFAULT_TIMEOUT_SECONDS = 60 * 60;

    public PreOrderDto saveOrder(PreOrderRequestDto preOrderRequestDto, Long orderCode) {

        PreOrderDto preOrderDto = PreOrderDto.of(preOrderRequestDto, orderCode);
        redisTemplate.opsForValue().set(PREFIX + preOrderDto.getOrderCode(),
                preOrderDto.toJson(objectMapper),
                DEFAULT_TIMEOUT_SECONDS,
                TimeUnit.SECONDS);

        return preOrderDto;
    }

    public PreOrderDto getOrder(Long orderCode) {
        String json = redisTemplate.opsForValue().get(PREFIX + orderCode);
        if (json == null) return null;
        return PreOrderDto.fromJson(json, objectMapper);
    }

    public void deleteOrder(Long orderCode) {
        redisTemplate.delete(PREFIX + orderCode);
    }

    public boolean isInvalidPreOrder(Long orderCode, Integer paidAmount) {
        return !isValidPreOrder(orderCode, paidAmount);
    }

    private boolean isValidPreOrder(Long orderCode, Integer paidAmount) {
        PreOrderDto preOrderDto = getOrder(orderCode);
        return preOrderDto != null && preOrderDto.equalsAmount(paidAmount);
    }
}
