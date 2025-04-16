package colorful.starbucks.member.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.member.application.InterestProductService;
import colorful.starbucks.member.dto.InterestProductDto;
import colorful.starbucks.member.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.member.vo.InterestProductVo;
import colorful.starbucks.member.vo.request.InterestProductAddRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interest-products")
@RequiredArgsConstructor
public class InterestProductController {

    private final InterestProductService interestProductService;

    private static final int DEFAULT_PAGE_SIZE = 10;

    @Operation(
            summary = "관심 상품 등록 API",
            description = "관심 상품을 등록하는 API 입니다.",
            tags = {"MEMBER-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> addInterestProduct(Authentication authentication,
                                                @RequestBody InterestProductAddRequestVo interestProductAddRequestVo) {

        interestProductService.addInterestProduct(
                InterestProductAddRequestDto.of(interestProductAddRequestVo, authentication.getName())
        );
        return ApiResponse.of(
                HttpStatus.CREATED,
                "관심 상품 등록을 완료했습니다.",
                null
        );
    }

    @Operation(
            summary = "관심 상품 리스트 조회 API",
            description = "관심 상품 리스트를 조회하는 API 입니다.",
            tags = {"MEMBER-SERVICE"}
    )
    @GetMapping
    public ApiResponse<PageResponse<InterestProductVo>> getInterestProducts(Authentication authentication,
                                                                            @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {
        return ApiResponse.of(HttpStatus.OK,
                "관심 상품 조회를 완료했습니다.",
                PageResponse.from(interestProductService.getInterestProductList(authentication.getName(), pageable)
                        .map(InterestProductDto::toVo)
                )
        );
    }

    @Operation(
            summary = "관심 상품 삭제 API",
            description = "관심 상품을 삭제하는 API 입니다.",
            tags = {"MEMBER-SERVICE"}
    )
    @DeleteMapping("/{interestProductId}")
    public ApiResponse<Void> removeInterestProduct(Authentication authentication,
                                                   @PathVariable Long interestProductId) {

        interestProductService.removeInterestProduct(interestProductId, authentication.getName());
        return ApiResponse.of(HttpStatus.NO_CONTENT,
                "관심 상품 삭제를 완료했습니다.",
                null);
    }
}
