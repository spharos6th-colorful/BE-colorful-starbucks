package colorful.starbucks.product.presentation;

import colorful.starbucks.admin.dto.ProductTopCategoryDtos;
import colorful.starbucks.admin.vo.ProductTopCategoryVo;
import colorful.starbucks.admin.vo.ProductTopCategoryVos;
import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.BestProductService;
import colorful.starbucks.product.vo.response.BestProductResponseVos;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products/best")
@RequiredArgsConstructor
public class BestProductController {

    private final BestProductService bestProductService;

    @Operation(
            summary = "카테고리별 베스트 상품 조회 API",
            description = "카테고리별 베스트 상품을 조회하는 API 입니다. " +
                    "카테고리 아이디를 전달하면 해당 카테고리의 베스트 상품을 조회합니다. " +
                    "상품 코드와 랭킹을 조회합니다.",
            tags = {"PRODUCT-SERVICE"}
    )
    @GetMapping
    public ApiResponse<BestProductResponseVos> getBestProductByCategoryId(@RequestParam Long categoryId) {
        return ApiResponse.ok("카테고리별 베스트 상품 조회가 완료되었습니다.",
                bestProductService.getBestProductByCategoryId(categoryId).toVo());
    }

    @Operation(
            summary = "베스트 상품 카테고리 조회 API",
            description = "베스트 상품 카테고리를 조회하는 API 입니다.",
            tags = {"PRODUCT-SERVICE"}
    )
    @GetMapping("/categories")
    public ApiResponse<ProductTopCategoryVos> getBestProductCategories() {
        return ApiResponse.ok("베스트 상품 카테고리 조회가 완료되었습니다.",
                bestProductService.getBestProductCategories().toVo());
    }
}
