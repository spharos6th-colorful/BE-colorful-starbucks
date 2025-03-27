package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.TermsAgreementService;
import colorful.starbucks.auth.dto.request.TermsAgreementRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/auth")
@RestController
@RequiredArgsConstructor
public class TermsAgreementController {

    private final TermsAgreementService termsAgreementService;

    @PostMapping("/terms-agreement")
    public void termsAgreement(@RequestBody TermsAgreementRequestDto dto) {
        termsAgreementService.saveTermsAgreement(dto.getAgreements(), dto.getMemberUuid());
    }
}
