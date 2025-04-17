package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductFilterService;
import colorful.starbucks.product.dto.request.ProductFilterCreateRequestDto;
import colorful.starbucks.product.vo.request.ProductFilterCreateRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-filter")
@RequiredArgsConstructor
public class ProductFilterController {

    private final ProductFilterService productFilterService;

    @Operation(
            summary = "상품 조회 테이블 등록 API",
            description = "상품 조회 테이블에 필터링에 필요한 데이터와 함께 상품을 등록하는 API 입니다.",
            tags = {"PRODUCT-FILTER-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createProductCategoryList(@RequestBody ProductFilterCreateRequestVo productFilterCreateRequestVo) {

        productFilterService.createProductCategoryList(ProductFilterCreateRequestDto.from(productFilterCreateRequestVo));
        return ApiResponse.ok("상품 필터 등록을 완료했습니다.", null);
    }
}
