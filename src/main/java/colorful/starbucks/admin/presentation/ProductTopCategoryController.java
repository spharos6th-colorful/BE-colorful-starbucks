package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductTopCategoryService;
import colorful.starbucks.admin.dto.request.ProductTopCategoryCreateRequestDto;
import colorful.starbucks.admin.vo.ProductTopCategoryVos;
import colorful.starbucks.admin.vo.request.ProductTopCategoryCreateRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/top-categories")
@RequiredArgsConstructor
public class ProductTopCategoryController {

    private final ProductTopCategoryService productTopCategoryService;

    @Operation(
            summary = "상위 카테고리 등록 API",
            description = "상위 카테고리 데이터를 등록하는 API 입니다.",
            tags = {"PRODUCT-CATEGORY-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createTopCategory(@RequestBody ProductTopCategoryCreateRequestVo productTopCategoryCreateRequestVo) {
        productTopCategoryService.createTopCategory(ProductTopCategoryCreateRequestDto.from(productTopCategoryCreateRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "최상위 카테고리 등록을 완료했습니다.",
                null);
    }

    @Operation(
            summary = "상위 카테고리 조회 API",
            description = "상위 카테고리 데이터를 조회하는 API 입니다.",
            tags = {"PRODUCT-CATEGORY-SERVICE"}
    )
    @GetMapping
    public ApiResponse<ProductTopCategoryVos> getTopCategories() {

        return ApiResponse.ok(
                "최상위 카테고리 조회를 완료했습니다.",
                productTopCategoryService.getTopCategories().toVo()
        );
    }
}
