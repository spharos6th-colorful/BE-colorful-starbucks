package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.TermsAgreementService;
import colorful.starbucks.auth.dto.request.TermsAgreementRequestDto;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class TermsAgreementController {

    private final TermsAgreementService termsAgreementService;

    @PostMapping("/terms-agreement")
    public ApiResponse<String> termsAgreement(@RequestBody TermsAgreementRequestDto termsAgreementRequestDto) {
        termsAgreementService.saveTermsAgreement(termsAgreementRequestDto.getAgreements(),
                                                 termsAgreementRequestDto.getMemberUuid());
        return ApiResponse.ok("이용약관 동의가 완료되었습니다.");
    }
}
