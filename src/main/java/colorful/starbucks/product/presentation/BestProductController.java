package colorful.starbucks.product.presentation;

import colorful.starbucks.admin.dto.ProductTopCategoryDtos;
import colorful.starbucks.admin.vo.ProductTopCategoryVo;
import colorful.starbucks.admin.vo.ProductTopCategoryVos;
import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.BestProductService;
import colorful.starbucks.product.vo.response.BestProductResponseVos;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products/best")
@RequiredArgsConstructor
public class BestProductController {

    private final BestProductService bestProductService;

    @GetMapping
    public ApiResponse<BestProductResponseVos> getBestProductByCategoryId(@RequestParam Long categoryId) {
        return ApiResponse.ok("카테고리별 베스트 상품 조회가 완료되었습니다.",
                bestProductService.getBestProductByCategoryId(categoryId).toVo());
    }

    @GetMapping("/categories")
    public ApiResponse<ProductTopCategoryVos> getBestProductCategories() {
        return ApiResponse.ok("베스트 상품 카테고리 조회가 완료되었습니다.",
                bestProductService.getBestProductCategories().toVo());
    }
}
