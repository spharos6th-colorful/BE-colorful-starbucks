package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlyProductDeleteRequestDto;
import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductAddResponseDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductListDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class RecentlyViewProductServiceImpl implements RecentlyViewProductService {

    private static final String KEY_SUFFIX = "recently-view-product:";
    private static final Integer ZSET_START_INDEX = 0;
    private static final Integer ZSET_END_INDEX = -1;
    private final RedisTemplate<String, Object> redisTemplate;
    private final ZSetOperations<String, Object> zSetOperations;

    public RecentlyViewProductServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public RecentlyViewProductAddResponseDto addRecentlyViewProduct(RecentlyViewProductAddRequestDto recentlyViewProductAddRequestDto) {
        zSetOperations.add(KEY_SUFFIX + recentlyViewProductAddRequestDto.getMemberUuid(),
                recentlyViewProductAddRequestDto.getProductCode(),
                System.currentTimeMillis());

        return RecentlyViewProductAddResponseDto.from(recentlyViewProductAddRequestDto.getProductThumbnailUrl());
    }

    @Override
    public List<RecentlyViewProductListDto> getRecentlyViewProductList(String memberUuid) {

        Map<LocalDate, List<Long>> recentlyViewProductMap = getRecentlyViewProductCodesOrderByCreatedAtDesc(memberUuid);

        return recentlyViewProductMap.entrySet().stream()
                .map(entry -> RecentlyViewProductListDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public void deleteRecentlyViewProduct(RecentlyProductDeleteRequestDto recentlyProductDeleteRequestDto) {
        zSetOperations.remove(KEY_SUFFIX + recentlyProductDeleteRequestDto.getMemberUuid(),
                recentlyProductDeleteRequestDto.getProductCode());
    }

    @Override
    public void deleteAllRecentlyViewProduct(String memberUuid) {
        redisTemplate.delete(KEY_SUFFIX + memberUuid);
    }

    private Map<LocalDate, List<Long>> getRecentlyViewProductCodesOrderByCreatedAtDesc(String memberUuid) {
        Set<ZSetOperations.TypedTuple<Object>> typedTuples =
                zSetOperations.reverseRangeWithScores(KEY_SUFFIX + memberUuid, ZSET_START_INDEX, ZSET_END_INDEX);

        Map<LocalDate, List<Long>> recentlyViewProductMap = new HashMap<>();
        typedTuples.stream()
                .forEach(typedTuple -> {
                    Long productCode = Long.parseLong(typedTuple.getValue().toString());
                    long timestamp = typedTuple.getScore().longValue();

                    LocalDate viewedAt = Instant.ofEpochMilli(timestamp)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    recentlyViewProductMap.computeIfAbsent(viewedAt, k -> new ArrayList<>()).add(productCode);
                });

        return recentlyViewProductMap;
    }
}
