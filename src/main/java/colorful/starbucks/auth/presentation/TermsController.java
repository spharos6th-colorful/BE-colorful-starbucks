package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.TermsService;
import colorful.starbucks.auth.dto.request.TermsAgreementRequestDto;
import colorful.starbucks.auth.dto.request.TermsCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/auth/")
@RestController
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    @PostMapping("/terms")
    public ResponseEntity<Void> createTerms(@RequestBody TermsCreateRequestDto termsCreateRequestDto) {
        termsService.createTerms(termsCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/terms-agreement")
    public void termsAgreement(@RequestBody TermsAgreementRequestDto dto) {
        termsService.saveTermsAgreement(dto.getAgreements(), dto.getMemberUuid());
    }



}
