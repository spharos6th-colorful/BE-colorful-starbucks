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
        zSetOperations.add(KEY_SUFFIX+ recentlySearchAddRequestDto.getMemberUuid(),
                            recentlySearchAddRequestDto.getSearch(),
                            System.currentTimeMillis());
    }

    @Override
    public List<RecentlySearchListDto> getRecentlySearch(String memberUuid) {
        Map<LocalDateTime, String> recentlySearchMap = getRecentlySearchOrderByCreatedAtDesc(memberUuid);
        return recentlySearchMap.entrySet().stream()
                .map(entry-> RecentlySearchListDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<LocalDateTime, String> getRecentlySearchOrderByCreatedAtDesc(String memberUuid) {
        Set<ZSetOperations.TypedTuple<Object>> typedTuples =
                zSetOperations.reverseRangeWithScores(KEY_SUFFIX + memberUuid, ZSET_START_INDEX, ZSET_END_INDEX);

        Map<LocalDateTime, String> recentlySearchMap = new HashMap<>();
        for (ZSetOperations.TypedTuple<Object> typedTuple : typedTuples) {

            String search = typedTuple.getValue().toString();
            long timestamp = typedTuple.getScore().longValue();

            LocalDateTime searchAt = Instant.ofEpochMilli(timestamp)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            recentlySearchMap.put(searchAt, search);
        }

        return recentlySearchMap;
    }
}
