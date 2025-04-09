package colorful.starbucks.member.presentation;

import colorful.starbucks.member.application.TermsService;
import colorful.starbucks.member.dto.request.TermsCreateRequestDto;
import colorful.starbucks.member.dto.response.TermsResponseDto;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    @PostMapping("/terms")
    public ApiResponse<Void> createTerms(@RequestBody TermsCreateRequestDto termsCreateRequestDto) {
        termsService.createTerms(termsCreateRequestDto);
        return ApiResponse.ok("약관 동의가 등록 되었습니다.",null);
    }

    @GetMapping("/terms")
    public ApiResponse<List<TermsResponseDto>> getTerms() {
        List<TermsResponseDto> terms = termsService.getTerms();
        return ApiResponse.ok("약관 조회를 완료하였습니다.",terms);
    }


}
