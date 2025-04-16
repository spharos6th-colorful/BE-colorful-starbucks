package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductFilterService;
import colorful.starbucks.product.dto.request.ProductFilterCreateRequestDto;
import colorful.starbucks.product.vo.request.ProductFilterCreateRequestVo;
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

    @PostMapping
    public ApiResponse<Void> createProductCategoryList(@RequestBody ProductFilterCreateRequestVo productFilterCreateRequestVo) {

        productFilterService.createProductCategoryList(ProductFilterCreateRequestDto.from(productFilterCreateRequestVo));
        return ApiResponse.ok("상품 필터 등록을 완료했습니다.", null);
    }
}
