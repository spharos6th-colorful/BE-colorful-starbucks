package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductTopCategoryService;
import colorful.starbucks.admin.dto.request.ProductTopCategoryCreateRequestDto;
import colorful.starbucks.admin.vo.ProductTopCategoryVos;
import colorful.starbucks.admin.vo.request.ProductTopCategoryCreateRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/top-categories")
@RequiredArgsConstructor
public class ProductTopCategoryController {

    private final ProductTopCategoryService productTopCategoryService;

    @PostMapping
    public ApiResponse<Void> createTopCategory(@RequestBody ProductTopCategoryCreateRequestVo productTopCategoryCreateRequestVo) {
        productTopCategoryService.createTopCategory(ProductTopCategoryCreateRequestDto.from(productTopCategoryCreateRequestVo));
        return ApiResponse.ok("최상위 카테고리 등록을 완료했습니다.", null);
    }

    @GetMapping
    public ApiResponse<ProductTopCategoryVos> getTopCategories() {

        return ApiResponse.ok(
                "최상위 카테고리 조회를 완료했습니다.",
                productTopCategoryService.getTopCategories().toVo()
        );
    }
}
