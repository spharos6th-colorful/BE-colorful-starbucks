package colorful.starbucks.member.application;

import colorful.starbucks.member.dto.response.RecentlyViewProductListDto;
import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductAddResponseDto;

import java.util.List;

public interface RecentlyViewProductService {

    RecentlyViewProductAddResponseDto addRecentlyViewProduct(RecentlyViewProductAddRequestDto recentlyViewProductAddRequestDto);
    List<RecentlyViewProductListDto> getRecentlyViewProductList(String memberUuid);
}
