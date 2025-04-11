package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.RecentlySearchService;
import colorful.starbucks.member.dto.request.RecentlySearchAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/recently-search")
@RequiredArgsConstructor
public class RecentlySearchController {

    private final RecentlySearchService recentlySearchService;

    @PostMapping("/{search}")
    public ApiResponse<Void> addRecentlySearch(Authentication authentication,
                                                  @PathVariable String search){
        recentlySearchService.addRecentlySearch(RecentlySearchAddRequestDto.from(authentication.getName(), search));
        return null;
    }

}
