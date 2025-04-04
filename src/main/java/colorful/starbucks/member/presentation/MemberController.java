package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.MemberService;
import colorful.starbucks.member.vo.response.MemberMyPageResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/my-page")
    public ApiResponse<MemberMyPageResponseVo> getMemberMyPage(Authentication authentication) {
        return ApiResponse.ok("마이페이지 조회를 완료했습니다.",
                memberService.getMyPage(authentication.getName()).toVo());

    }


}
