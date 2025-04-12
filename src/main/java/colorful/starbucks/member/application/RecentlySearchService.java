package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import colorful.starbucks.member.dto.request.RecentlySearchDeleteRequestDto;
import colorful.starbucks.member.dto.response.RecentlySearchListDto;

import java.util.List;

public interface RecentlySearchService {

    void addRecentlySearchKeyword(RecentlySearchAddRequestDto recentlySearchAddRequestDto);
    List<RecentlySearchListDto> getRecentlySearchKeywords(String memberUuid);
    void deleteRecentlySearchKeyword(RecentlySearchDeleteRequestDto recentlySearchDeleteRequestDto);
    void deleteAllRecentlySearchKeywords(String memberUuid);
}
