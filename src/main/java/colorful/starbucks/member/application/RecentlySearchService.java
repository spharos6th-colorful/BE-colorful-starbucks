package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;

public interface RecentlySearchService {

    void addRecentlySearch(RecentlySearchAddRequestDto recentlySearchAddRequestDto);
}
