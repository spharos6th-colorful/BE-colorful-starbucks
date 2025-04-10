package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductAddResponseDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RecentlyViewProductServiceImpl implements RecentlyViewProductService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ZSetOperations<String, Object> zSetOperations;

    private static final String KEY_SUFFIX = "recently-view-product:";

    public RecentlyViewProductServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public RecentlyViewProductAddResponseDto addRecentlyViewProduct(RecentlyViewProductAddRequestDto recentlyViewProductAddRequestDto) {
        zSetOperations.add(KEY_SUFFIX + recentlyViewProductAddRequestDto.getMemberUuid()
                , recentlyViewProductAddRequestDto.getProductCode()
                , getZSetScore());

        return RecentlyViewProductAddResponseDto.of(recentlyViewProductAddRequestDto.getProductThumbnailUrl());
    }

    private double getZSetScore() {
        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return Double.parseDouble(currentDateTime);
    }
}
