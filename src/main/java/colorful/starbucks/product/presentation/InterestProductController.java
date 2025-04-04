package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.product.application.InterestProductService;
import colorful.starbucks.product.dto.InterestProductDto;
import colorful.starbucks.product.dto.request.InterestProductAddRequestDto;
import colorful.starbucks.product.vo.InterestProductVo;
import colorful.starbucks.product.vo.request.InterestProductAddRequestVo;
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

    @GetMapping
    public ApiResponse<PageResponse<InterestProductVo>> getInterestProducts(Authentication authentication,
                                                                            @PageableDefault(size = 3) Pageable pageable) {
        return ApiResponse.of(HttpStatus.OK,
                "관심 상품 조회를 완료했습니다.",
                PageResponse.from(interestProductService.getInterestProductList(authentication.getName(), pageable)
                        .map(InterestProductDto::toVo)
                )
        );
    }

    @DeleteMapping("/{interestProductId}")
    public ApiResponse<Void> removeInterestProduct(Authentication authentication,
                                                   @PathVariable Long interestProductId) {

        interestProductService.removeInterestProduct(interestProductId, authentication.getName());
        return ApiResponse.of(HttpStatus.NO_CONTENT,
                "관심 상품 삭제를 완료했습니다.",
                null);
    }
}
