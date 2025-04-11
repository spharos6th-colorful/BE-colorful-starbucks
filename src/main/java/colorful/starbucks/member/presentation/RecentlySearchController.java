package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.RecentlySearchService;
import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlySearchListResponseDto;
import colorful.starbucks.member.vo.response.RecentlySearchListResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/recently-search")
@RequiredArgsConstructor
public class RecentlySearchController {

    private final RecentlySearchService recentlySearchService;

    @PostMapping
    public ApiResponse<Void> addRecentlySearch(Authentication authentication,
                                                  @RequestParam String search){
        recentlySearchService.addRecentlySearch(RecentlySearchAddRequestDto.of(authentication.getName(), search));
        return ApiResponse.ok("최근 검색어 등록이 완료 되었습니다.",
                null);
    }

    @GetMapping
    public ApiResponse<RecentlySearchListResponseVo> getRecentlySearchList(Authentication authentication){

        return ApiResponse.ok("최근 검색어 조회가 완료 되었습니다.",
                RecentlySearchListResponseDto.from(
                        recentlySearchService.getRecentlySearch(authentication.getName())
                ).toVo());
    }

}
