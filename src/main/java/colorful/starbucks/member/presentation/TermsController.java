package colorful.starbucks.member.presentation;

import colorful.starbucks.member.application.TermsService;
import colorful.starbucks.member.domain.TermsCategory;
import colorful.starbucks.member.dto.request.TermsCreateRequestDto;
import colorful.starbucks.member.dto.response.TermsResponseDto;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    @Operation(
            summary = "약관 생성 API",
            description = "약관을 생성하는 API 입니다.",
            tags = {"MEMBER-SERVICE"}
    )
    @PostMapping("/terms")
    public ApiResponse<Void> createTerms(@RequestBody TermsCreateRequestDto termsCreateRequestDto) {
        termsService.createTerms(termsCreateRequestDto);
        return ApiResponse.ok("새로운 약관을 생성했습니다.",null);
    }

    @Operation(
            summary = "약관 조회 API",
            description = "약관을 조회하는 API 입니다.",
            tags = {"MEMBER-SERVICE"}
    )
    @GetMapping("/terms")
    public ApiResponse<List<TermsResponseDto>> getTerms() {
        List<TermsResponseDto> terms = termsService.getTerms();
        return ApiResponse.ok("약관 조회를 완료하였습니다.",terms);
    }

    @GetMapping("/terms/{category}")
    public ApiResponse<List<TermsResponseDto>> getTermsByCategory(@PathVariable TermsCategory category) {
        List<TermsResponseDto> terms = termsService.getTermsByCategory(category);
        return ApiResponse.ok("약관 조회 완료", terms);
    }




}
