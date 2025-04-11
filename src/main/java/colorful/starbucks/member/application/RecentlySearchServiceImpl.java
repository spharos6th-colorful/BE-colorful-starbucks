package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

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
}
