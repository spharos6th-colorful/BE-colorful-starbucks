package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.TermsService;
import colorful.starbucks.auth.dto.request.TermsCreateRequestDto;
import colorful.starbucks.auth.dto.response.TermsResponseDto;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
