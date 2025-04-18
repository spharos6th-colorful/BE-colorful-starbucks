package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.admin.vo.ProductSearchListFilterVo;
import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.application.ProductFilterService;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;
import colorful.starbucks.product.vo.response.ProductCursorResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductFilterService productFilterService;

    @GetMapping
    public ApiResponse<CursorPage<ProductCursorResponseVo>> searchProducts(@ModelAttribute ProductSearchListFilterVo productSearchListFilterVo) {
        CursorPage<ProductCursorResponseDto> response = productFilterService.getSearchedProductList(ProductSearchListFilterDto.from(productSearchListFilterVo));
        return ApiResponse.ok("검색으로 상품 목록 조회를 완료 했습니다.",
                CursorPage.<ProductCursorResponseVo>builder()
                        .content(response.getContent().stream()
                        .map(ProductCursorResponseDto::toVo)
                                .toList())
                        .nextCursor(response.getNextCursor())
                        .hasNext(response.getHasNext())
                        .build());
    }
}
