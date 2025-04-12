package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.RecentlySearchService;
import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import colorful.starbucks.member.dto.request.RecentlySearchDeleteRequestDto;
import colorful.starbucks.member.dto.response.RecentlySearchListResponseDto;
import colorful.starbucks.member.vo.response.RecentlySearchListResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users/recently-search")
@RequiredArgsConstructor
public class RecentlySearchController {

    private final RecentlySearchService recentlySearchService;

    @PostMapping
    public ApiResponse<Void> addRecentlySearchKeyword(Authentication authentication,
                                                  @RequestParam String keyword){
        recentlySearchService.addRecentlySearchKeyword(RecentlySearchAddRequestDto.of(authentication.getName(), keyword));
        return ApiResponse.ok("최근 검색어 등록이 완료 되었습니다.",
                null);
    }

    @GetMapping
    public ApiResponse<RecentlySearchListResponseVo> getRecentlySearchKeywordList(Authentication authentication){

        return ApiResponse.ok("최근 검색어 조회가 완료 되었습니다.",
                RecentlySearchListResponseDto.from(
                        recentlySearchService.getRecentlySearchKeywords(authentication.getName())
                ).toVo());
    }

    @DeleteMapping("/{keyword}")
    public ApiResponse<Void> deleteRecentlySearchKeyword(Authentication authentication,
                                                    @PathVariable String keyword){


        recentlySearchService.deleteRecentlySearchKeyword(RecentlySearchDeleteRequestDto.of(authentication.getName(), keyword));
        return ApiResponse.ok("최근 검색어 삭제가 완료 되었습니다.",
                null);
    }

    @DeleteMapping
    public ApiResponse<Void> deleteAllRecentlySearchKeywords(Authentication authentication){

        recentlySearchService.deleteAllRecentlySearchKeywords(authentication.getName());
        return ApiResponse.of(
                HttpStatus.NO_CONTENT,
                "최근 검색어 전체 삭제를 완료 했습니다.",
                null);
    }

}
