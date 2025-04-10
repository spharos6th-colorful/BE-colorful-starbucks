package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductAddResponseDto;

public interface RecentlyViewProductService {
    RecentlyViewProductAddResponseDto addRecentlyViewProduct(RecentlyViewProductAddRequestDto recentlyViewProductAddRequestDto);
}
