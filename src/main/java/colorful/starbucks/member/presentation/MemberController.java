package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.member.application.MemberService;
import colorful.starbucks.member.dto.request.MemberMyPageEditRequestDto;
import colorful.starbucks.member.vo.request.MemberMyPageEditRequestVo;
import colorful.starbucks.member.vo.response.MemberMyPageResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/my-page")
    public ApiResponse<MemberMyPageResponseVo> getMyPage(Authentication authentication) {
        return ApiResponse.ok("마이페이지 조회를 완료했습니다.",
                memberService.getMyPage(authentication.getName()).toVo());

    }

    @PutMapping("/my-page")
    public ApiResponse<Void> editMyPage(Authentication authentication,
                                        @RequestBody MemberMyPageEditRequestVo memberMyPageEditRequestVo) {
        memberService.editMyPage(MemberMyPageEditRequestDto.of(memberMyPageEditRequestVo, authentication.getName()));
        return ApiResponse.ok("마이페이지 전화번호 및 닉네임 변경을 완료했습니다.",
                null);
    }


}
