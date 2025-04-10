package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.RecentlyViewProductService;
import colorful.starbucks.member.dto.response.RecentlyViewProductListDto;
import colorful.starbucks.member.dto.request.RecentlyViewProductAddRequestDto;
import colorful.starbucks.member.vo.request.RecentlyViewProductAddRequestVo;
import colorful.starbucks.member.vo.response.RecentlyViewProductListVo;
import colorful.starbucks.member.vo.response.RecentlyViewProductAddResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/recently-view-products")
@RequiredArgsConstructor
public class RecentlyViewProductController {

    private final RecentlyViewProductService recentlyViewProductService;

    @PostMapping
    public ApiResponse<RecentlyViewProductAddResponseVo> addRecentlyViewProduct(Authentication authentication,
                                                                                @RequestBody RecentlyViewProductAddRequestVo recentlyViewProductAddRequestVo) {

        return ApiResponse.of(HttpStatus.CREATED,
                "최근 본 상품을 추가했습니다.",
                recentlyViewProductService.addRecentlyViewProduct(
                        RecentlyViewProductAddRequestDto.of(authentication.getName(), recentlyViewProductAddRequestVo))
                        .toVo()
        );
    }

    @GetMapping
    public ApiResponse<List<RecentlyViewProductListVo>> getRecentlyViewProductList(Authentication authentication) {

        return ApiResponse.ok("최근 본 상품 리스트를 조회했습니다.",
            recentlyViewProductService.getRecentlyViewProductList(authentication.getName()).stream()
                    .map(RecentlyViewProductListDto::toVo)
                    .toList()
        );
    }
}
