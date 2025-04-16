package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductDetailService;
import colorful.starbucks.product.dto.request.ProductDetailCodeAndQuantityRequestDto;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.vo.request.ProductDetailCodeAndQuantityRequestVo;
import colorful.starbucks.product.vo.request.ProductDetailCreateRequestVo;
import colorful.starbucks.product.vo.response.ProductDetailCodeAndQuantityResponseVo;
import colorful.starbucks.product.vo.response.ProductDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/product-details")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @Operation(summary = "상세 상품 등록 API",
            description = "상품 코드, 옵션, 수량 등 다양한 데이터로 상세 상품을 등록하는 API 입니다.",
            tags = {"PRODUCT-DETAIL-SERVICE"})
    @PostMapping
    public ApiResponse<ProductDetailResponseVo> createProductDetail(@RequestPart ProductDetailCreateRequestVo productDetailCreateRequestVo,
                                                                    @RequestPart MultipartFile productDetailThumbnail) {

        return ApiResponse.ok("상세 상품이 등록되었습니다.",
                productDetailService.createProductDetail(
                        ProductDetailCreateRequestDto.from(productDetailCreateRequestVo),
                        productDetailThumbnail).toVo()
        );
    }

    @Operation(summary = "상세 상품 조회 API",
            description = "상세 상품 코드로 상세 상품에 대한 정보를 조회하는 API 입니다.",
            tags = {"PRODUCT-DETAIL-SERVICE"})
    @GetMapping("/{productDetailCode}")
    public ApiResponse<ProductDetailResponseVo> getProductDetail(@PathVariable Long productDetailCode) {
        return ApiResponse.ok("상세 상품이 조회되었습니다.",
                productDetailService.getProductDetail(productDetailCode).toVo()
        );
    }

    @Operation(summary = "상세 상품 코드, 수량 조회 API",
            description = "상품 코드, 옵션으로 상세 상품 코드와 수량을 조회하는 API 입니다.",
            tags = {"PRODUCT-DETAIL-SERVICE"})
    @GetMapping
    public ApiResponse<ProductDetailCodeAndQuantityResponseVo> getProductDetailWithOptions(
            @ModelAttribute ProductDetailCodeAndQuantityRequestVo productDetailCodeAndQuantityRequestVo) {

        return ApiResponse.ok("상세 상품이 조회되었습니다.",
                productDetailService.getProductDetailWithOptions(
                        ProductDetailCodeAndQuantityRequestDto.from(productDetailCodeAndQuantityRequestVo)
                ).toVo());
    }
}
