package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductCategoryListService;
import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.dto.response.ProductCategoryCursorResponseDto;
import colorful.starbucks.admin.vo.response.ProductCategoryCursorResponseVo;
import colorful.starbucks.admin.vo.ProductCategoryListFilterVo;
import colorful.starbucks.admin.vo.request.ProductCategoryListCreateRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-category-lists")
@RequiredArgsConstructor
public class ProductCategoryListController {

    private final ProductCategoryListService productCategoryListService;

    @PostMapping
    public ApiResponse<Void> createProductCategoryList(@RequestBody ProductCategoryListCreateRequestVo productCategoryListCreateRequestVo) {

        productCategoryListService.createProductCategoryList(ProductCategoryListCreateRequestDto.from(productCategoryListCreateRequestVo));
        return ApiResponse.ok("상품 카테고리 등록을 완료했습니다.", null);
    }

    @GetMapping
    public ApiResponse<CursorPage<ProductCategoryCursorResponseVo>> getProductCategoryList(@ModelAttribute ProductCategoryListFilterVo productCategoryListFilterVo) {
        CursorPage<ProductCategoryCursorResponseDto> response = productCategoryListService.getFilteredProductList(ProductCategoryListFilterDto.from(productCategoryListFilterVo));
        return ApiResponse.ok("상품 카테고리 목록 조회를 완료했습니다.",
                CursorPage.<ProductCategoryCursorResponseVo>builder()
                        .content(response.getContent()
                                .stream()
                                .map(ProductCategoryCursorResponseDto::toVo)
                                .toList())
                        .nextCursor(response.getNextCursor())
                        .hasNext(response.isHasNext())
                        .build()
        );
    }
}
