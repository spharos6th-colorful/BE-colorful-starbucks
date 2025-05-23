package colorful.starbucks.member.presentation;

import colorful.starbucks.member.application.TermsAgreementService;
import colorful.starbucks.member.dto.request.TermsAgreementRequestDto;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(
            summary = "이용약관 동의 API",
            description = "이용약관 동의 API 입니다.",
            tags = {"MEMBER-SERVICE"}
    )
    @PostMapping("/terms-agreement")
    public ApiResponse<Void> termsAgreement(@RequestBody TermsAgreementRequestDto termsAgreementRequestDto) {
        termsAgreementService.saveTermsAgreement(termsAgreementRequestDto.getAgreements(),
                                                 termsAgreementRequestDto.getMemberUuid());
        return ApiResponse.ok("이용약관 동의가 완료되었습니다.",null);
    }
}
