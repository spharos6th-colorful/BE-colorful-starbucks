package colorful.starbucks.admin.presentation;

import colorful.starbucks.admin.application.ProductOptionService;
import colorful.starbucks.admin.dto.request.ProductColorAddRequestDto;
import colorful.starbucks.admin.dto.request.ProductSizeAddRequestDto;
import colorful.starbucks.admin.vo.request.ProductColorAddRequestVo;
import colorful.starbucks.admin.vo.request.ProductSizeAddRequestVo;
import colorful.starbucks.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product/option")
@RequiredArgsConstructor
public class ProductOptionController {

    private final ProductOptionService productOptionService;

    @Operation(
            summary = "색상 옵션 등록 API",
            description = "색상 옵션 데이터를 등록하는 API 입니다.",
            tags = {"PRODUCT-OPTION-SERVICE"}
    )
    @PostMapping("/colors")
    public ApiResponse<Void> createColorOption(@RequestBody ProductColorAddRequestVo productColorAddRequestVo) {
        productOptionService.createColorOption(ProductColorAddRequestDto.from(productColorAddRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "색상 옵션 등록을 완료했습니다.",
                null);
    }

    @Operation(
            summary = "사이즈 옵션 등록 API",
            description = "사이즈 옵션 데이터를 등록하는 API 입니다.",
            tags = {"PRODUCT-OPTION-SERVICE"}
    )
    @PostMapping("/sizes")
    public ApiResponse<Void> createSizeOption(@RequestBody ProductSizeAddRequestVo productSizeAddRequestVo) {
        productOptionService.createSizeOption(ProductSizeAddRequestDto.from(productSizeAddRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "사이즈 옵션 등록을 완료했습니다.",
                null);
    }
}
