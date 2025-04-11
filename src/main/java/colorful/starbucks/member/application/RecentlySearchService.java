package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlySearchListDto;

import java.util.List;

public interface RecentlySearchService {

    void addRecentlySearch(RecentlySearchAddRequestDto recentlySearchAddRequestDto);
    List<RecentlySearchListDto> getRecentlySearch(String memberUuid);
}
