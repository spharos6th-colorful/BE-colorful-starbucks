package colorful.starbucks.auth.presentation;

import colorful.starbucks.auth.application.TermsService;
import colorful.starbucks.auth.dto.request.TermsCreateRequestDto;
import colorful.starbucks.auth.dto.response.TermsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/auth")
@RestController
@RequiredArgsConstructor
public class TermsController {

    private final TermsService termsService;

    @PostMapping("/terms")
    public ResponseEntity<Void> createTerms(@RequestBody TermsCreateRequestDto termsCreateRequestDto) {
        termsService.createTerms(termsCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/terms")
    public ResponseEntity<List<TermsResponseDto>> getTerms() {
        List<TermsResponseDto> terms = termsService.getTerms();
        return ResponseEntity.ok(terms);
    }


}
