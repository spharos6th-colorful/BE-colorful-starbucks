package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.InterestProductService;
import colorful.starbucks.product.dto.request.InterestProductCreateRequestDto;
import colorful.starbucks.product.vo.request.InterestProductCreateRequestVo;
import colorful.starbucks.product.vo.response.InterestProductCreateResponseVo;
import colorful.starbucks.product.vo.response.InterestProductListResponseVo;
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

    @GetMapping
    public ApiResponse<InterestProductListResponseVo> getInterestProducts(Authentication authentication,
                                                                          @PageableDefault(size = 3) Pageable pageable) {
        return ApiResponse.of(HttpStatus.OK,
                "관심 상품 조회를 완료했습니다.",
                interestProductService.getInterestProductList(authentication.getName(), pageable).toVo()
        );
    }

    @PostMapping
    public ApiResponse<InterestProductCreateResponseVo> createInterestProduct(
            @RequestBody InterestProductCreateRequestVo interestProductCreateRequestVo,
            Authentication authentication) {

        return ApiResponse.of(
                HttpStatus.CREATED,
                "관심 상품 등록을 완료했습니다." ,
                interestProductService.createInterestProduct(
                                InterestProductCreateRequestDto.from(interestProductCreateRequestVo, authentication.getName()))
                        .toVo()
        );
    }

    @DeleteMapping("/{productCode}")
    public ApiResponse<Void> removeInterestProduct(Authentication authentication,
                                                   @PathVariable String productCode) {

        interestProductService.removeInterestProduct(authentication.getName(), productCode);
        return ApiResponse.of(HttpStatus.NO_CONTENT, "관심 상품 삭제를 완료했습니다.", null);
    }
}
