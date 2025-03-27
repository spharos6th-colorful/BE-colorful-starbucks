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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interest-products")
@RequiredArgsConstructor
public class InterestProductController {

    private final InterestProductService interestProductService;

    @GetMapping("/{memberUuid}")
    public ApiResponse<InterestProductListResponseVo> getInterestProducts(@PathVariable String memberUuid,
                                                                          @PageableDefault(size = 3, sort = "createdAt") Pageable pageable) {
        return ApiResponse.of(HttpStatus.OK,
                "관심 상품 조회를 완료했습니다.",
                interestProductService.getInterestProductList(memberUuid, pageable).toVo()
        );
    }

    // security 완료되면 memberUuid는 authentication에서 가져오도록 수정
    @PostMapping("/{memberUuid}")
    public ApiResponse<InterestProductCreateResponseVo> createInterestProduct(
            @RequestBody InterestProductCreateRequestVo interestProductCreateRequestVo,
            @PathVariable String memberUuid) {

        return ApiResponse.of(
                HttpStatus.CREATED,
                "관심 상품 등록을 완료했습니다." ,
                interestProductService.createInterestProduct(
                                InterestProductCreateRequestDto.from(interestProductCreateRequestVo, memberUuid))
                        .toVo()
        );
    }

    @DeleteMapping("/{memberUuid}/{productCode}")
    public ApiResponse<Void> removeInterestProduct(@PathVariable String memberUuid,
                                                   @PathVariable String productCode) {

        interestProductService.removeInterestProduct(memberUuid, productCode);
        return ApiResponse.of(HttpStatus.NO_CONTENT, "관심 상품 삭제를 완료했습니다.", null);
    }
}
