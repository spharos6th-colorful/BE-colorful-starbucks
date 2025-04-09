package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductCategoryListService;
import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.dto.response.ProductCursorResponseDto;
import colorful.starbucks.admin.vo.ProductCategoryListFilterVo;
import colorful.starbucks.admin.vo.request.ProductCategoryListCreateRequestVo;
import colorful.starbucks.admin.vo.response.ProductCursorResponseVo;
import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-category-list")
@RequiredArgsConstructor
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    @PostMapping
    public ApiResponse<Void> createProductCategoryList(@RequestBody ProductCategoryListCreateRequestVo productCategoryListCreateRequestVo) {

        productCategoryListService.createProductCategoryList(ProductCategoryListCreateRequestDto.from(productCategoryListCreateRequestVo));
        return ApiResponse.ok("상품 카테고리 등록을 완료했습니다.", null);
    }

    @GetMapping
    public ApiResponse<CursorPage<ProductCursorResponseVo>> getProductCategoryList(@ModelAttribute ProductCategoryListFilterVo productCategoryListFilterVo) {
        CursorPage<ProductCursorResponseDto> response = productCategoryListService.getFilteredProductList(ProductCategoryListFilterDto.from(productCategoryListFilterVo));
        return ApiResponse.ok("상품 카테고리 목록 조회를 완료했습니다.",
                CursorPage.<ProductCursorResponseVo>builder()
                        .content(response.getContent().stream()
                                .map(ProductCursorResponseDto::toVo)
                                .toList())
                        .nextCursor(response.getNextCursor())
                        .hasNext(response.getHasNext())
                        .build()
        );
    }
}
