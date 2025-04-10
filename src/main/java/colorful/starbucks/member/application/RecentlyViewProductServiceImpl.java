package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.response.RecentlyViewProductListDto;
import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductAddResponseDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class RecentlyViewProductServiceImpl implements RecentlyViewProductService {

    private final ZSetOperations<String, Object> zSetOperations;

    private static final String KEY_SUFFIX = "recently-view-product:";

    public RecentlyViewProductServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public RecentlyViewProductAddResponseDto addRecentlyViewProduct(RecentlyViewProductAddRequestDto recentlyViewProductAddRequestDto) {
        zSetOperations.add(KEY_SUFFIX + recentlyViewProductAddRequestDto.getMemberUuid(),
                recentlyViewProductAddRequestDto.getProductCode(),
                System.currentTimeMillis());

        return RecentlyViewProductAddResponseDto.of(recentlyViewProductAddRequestDto.getProductThumbnailUrl());
    }

    @Override
    public List<RecentlyViewProductListDto> getRecentlyViewProductList(String memberUuid) {

        Map<LocalDate, List<Long>> recentlyViewProductMap = getRecentlyViewProductCodesOrderByCreatedAtDesc(memberUuid);

        return recentlyViewProductMap.entrySet().stream()
                .map(entry -> RecentlyViewProductListDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<LocalDate, List<Long>> getRecentlyViewProductCodesOrderByCreatedAtDesc(String memberUuid) {
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = zSetOperations.reverseRangeWithScores(KEY_SUFFIX + memberUuid, 0, -1);

        Map<LocalDate, List<Long>> recentlyViewProductMap = new HashMap<>();
        for (ZSetOperations.TypedTuple<Object> typedTuple : typedTuples) {

            Long productCode = Long.parseLong(typedTuple.getValue().toString());
            long timestamp = typedTuple.getScore().longValue();

            LocalDate viewedAt = Instant.ofEpochMilli(timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            recentlyViewProductMap.computeIfAbsent(viewedAt, k -> new ArrayList<>()).add(productCode);
        }

        return recentlyViewProductMap;
    }
}
