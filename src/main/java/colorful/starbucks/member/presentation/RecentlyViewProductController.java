package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.RecentlyViewProductService;
import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.dto.response.RecentlyViewProductAddResponseDto;
import colorful.starbucks.member.vo.request.RecentlyViewProductAddRequestVo;
import colorful.starbucks.member.vo.response.RecentlyViewProductAddResponseVo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/recently-view-products")
@RequiredArgsConstructor
public class RecentlyViewProductController {

    private final RecentlyViewProductService recentlyViewProductService;

    private static final String ANONYMOUS_MEMBER_COOKIE_KEY = "MemberKey";

    @PostMapping
    public ApiResponse<RecentlyViewProductAddResponseVo> addRecentlyViewProduct(Authentication authentication,
                                                                                @RequestBody RecentlyViewProductAddRequestVo recentlyViewProductAddRequestVo,
                                                                                HttpServletRequest request,
                                                                                HttpServletResponse response) {

        String memberUuid;
        String memberKey = getMemberKeyFromCookies(request);

        if (authentication == null && memberKey == null) {
            String signature = UUID.randomUUID().toString();
            response.addCookie(new Cookie(ANONYMOUS_MEMBER_COOKIE_KEY, signature));
            memberUuid = signature;
        } else if (memberKey != null) {
            memberUuid = memberKey;
        } else {
            memberUuid = authentication.getName();
        }

        return ApiResponse.ok("최근 본 상품을 추가했습니다.",
                recentlyViewProductService.addRecentlyViewProduct(
                        RecentlyViewProductAddRequestDto.of(memberUuid, recentlyViewProductAddRequestVo))
                        .toVo()
        );
    }

    private String getMemberKeyFromCookies(HttpServletRequest request) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("MemberKey".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
