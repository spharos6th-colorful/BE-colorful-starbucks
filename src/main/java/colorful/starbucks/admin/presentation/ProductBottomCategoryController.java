package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductBottomCategoryService;
import colorful.starbucks.admin.dto.request.ProductBottomCategoryCreateRequestDto;
import colorful.starbucks.admin.vo.request.ProductBottomCategoryCreateRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bottom-categories")
@RequiredArgsConstructor
public class ProductBottomCategoryController {

    private final ProductBottomCategoryService productBottomCategoryService;

    @PostMapping
    public ApiResponse<Void> createBottomCategory(@RequestBody ProductBottomCategoryCreateRequestVo productBottomCategoryCreateRequestVo) {
        productBottomCategoryService.createBottomCategory(ProductBottomCategoryCreateRequestDto.from(productBottomCategoryCreateRequestVo));
        return ApiResponse.ok("하위 카테고리 등록을 완료했습니다.", null);
    }
}
