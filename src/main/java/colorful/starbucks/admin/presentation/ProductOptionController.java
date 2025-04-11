package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductOptionService;
import colorful.starbucks.admin.dto.request.ProductColorAddRequestDto;
import colorful.starbucks.admin.dto.request.ProductSizeAddRequestDto;
import colorful.starbucks.admin.vo.request.ProductColorAddRequestVo;
import colorful.starbucks.admin.vo.request.ProductSizeAddRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product/option")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @PostMapping("/colors")
    public ApiResponse<Void> createColorOption(@RequestBody ProductColorAddRequestVo productColorAddRequestVo) {
        productOptionService.createColorOption(ProductColorAddRequestDto.from(productColorAddRequestVo));
        return ApiResponse.ok("색상 옵션 등록을 완료했습니다.", null);
    }

    @PostMapping("/sizes")
    public ApiResponse<Void> createSizeOption(@RequestBody ProductSizeAddRequestVo productSizeAddRequestVo) {
        productOptionService.createSizeOption(ProductSizeAddRequestDto.from(productSizeAddRequestVo));
        return ApiResponse.ok("사이즈 옵션 등록을 완료했습니다.", null);
    }
}
