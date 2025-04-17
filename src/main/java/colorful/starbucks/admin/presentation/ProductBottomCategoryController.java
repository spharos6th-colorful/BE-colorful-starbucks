package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductBottomCategoryService;
import colorful.starbucks.admin.dto.request.ProductBottomCategoryCreateRequestDto;
import colorful.starbucks.admin.vo.ProductBottomCategoryVos;
import colorful.starbucks.admin.vo.request.ProductBottomCategoryCreateRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bottom-categories")
@RequiredArgsConstructor
public class ProductBottomCategoryController {

    private final ProductBottomCategoryService productBottomCategoryService;

    @Operation(
            summary = "하위 카테고리 등록 API",
            description = "하위 카테고리 데이터를 등록하는 API 입니다.",
            tags = {"PRODUCT-CATEGORY-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createBottomCategory(@RequestBody ProductBottomCategoryCreateRequestVo productBottomCategoryCreateRequestVo) {
        productBottomCategoryService.createBottomCategory(ProductBottomCategoryCreateRequestDto.from(productBottomCategoryCreateRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "하위 카테고리 등록을 완료했습니다.",
                null);
    }

    @Operation(
            summary = "하위 카테고리 조회 API",
            description = "하위 카테고리 데이터를 조회하는 API 입니다.",
            tags = {"PRODUCT-CATEGORY-SERVICE"}
    )
    @GetMapping
    public ApiResponse<ProductBottomCategoryVos> getBottomCategories(@RequestParam Long topCategoryId) {

        return ApiResponse.ok("하위 카테고리 조회를 완료했습니다.",
                productBottomCategoryService.getBottomCategories(topCategoryId).toVo());
    }
}
