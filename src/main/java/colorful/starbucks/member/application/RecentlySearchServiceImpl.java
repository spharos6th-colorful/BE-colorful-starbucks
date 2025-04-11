package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlySearchListDto;
import colorful.starbucks.member.dto.response.RecentlySearchListResponseDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class RecentlySearchServiceImpl implements RecentlySearchService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ZSetOperations<String, Object> zSetOperations;

    private static final String KEY_SUFFIX = "recently-search:";
    private static final Integer ZSET_START_INDEX = 0;
    private static final Integer ZSET_END_INDEX = -1;

    public RecentlySearchServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    @Override
    public void addRecentlySearch(RecentlySearchAddRequestDto recentlySearchAddRequestDto) {
        zSetOperations.add(KEY_SUFFIX + recentlySearchAddRequestDto.getMemberUuid(),
                recentlySearchAddRequestDto.getSearch(),
                System.currentTimeMillis());
    }

    @Override
    public List<RecentlySearchListDto> getRecentlySearch(String memberUuid) {

        Set<ZSetOperations.TypedTuple<Object>> typedTuples =
                zSetOperations.reverseRangeWithScores(KEY_SUFFIX + memberUuid, ZSET_START_INDEX, ZSET_END_INDEX + 10);

        return typedTuples.stream()
                .map(tuple -> {
                    String search = tuple.getValue().toString();
                    long timestamp = tuple.getScore().longValue();
                    LocalDateTime searchAt = Instant.ofEpochMilli(timestamp)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDateTime();
                    return RecentlySearchListDto.of(searchAt, search);
                })
                .toList();
    }

}
