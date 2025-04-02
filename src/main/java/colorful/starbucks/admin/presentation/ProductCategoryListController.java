package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductCategoryListService;
import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.vo.request.ProductCategoryListCreateRequestVo;
import colorful.starbucks.common.response.ApiResponse;
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
}
