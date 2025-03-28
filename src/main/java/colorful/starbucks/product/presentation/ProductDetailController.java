package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductDetailService;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.vo.request.ProductDetailCreateRequestVo;
import colorful.starbucks.product.vo.response.ProductDetailCodeAndQuantityResponseVo;
import colorful.starbucks.product.vo.response.ProductDetailCreateResponseVo;
import colorful.starbucks.product.vo.response.ProductOptionListResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/product-details")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @PostMapping("/{productCode}")
    public ApiResponse<ProductDetailCreateResponseVo> createProductDetail(
            @PathVariable("productCode") String productCode,
            @RequestPart ProductDetailCreateRequestVo productDetailCreateRequestVo,
            @RequestPart MultipartFile productDetailThumbnail) {

        return ApiResponse.ok("상세 상품이 등록되었습니다.",
                productDetailService.createProductDetail(
                        ProductDetailCreateRequestDto.from(productDetailCreateRequestVo, productCode),
                        productDetailThumbnail).toVo()
        );
    }

    @GetMapping("/{productCode}")
    public ApiResponse<ProductDetailCodeAndQuantityResponseVo> getProductDetailWithOptions(
            @PathVariable String productCode,
            @RequestParam int sizeId,
            @RequestParam int colorId) {

        return ApiResponse.ok("상세 상품이 조회되었습니다.",
                productDetailService.getProductDetailWithOptions(productCode, sizeId, colorId).toVo()
        );
    }

    @GetMapping("/{productCode}/options")
    public ApiResponse<ProductOptionListResponseVo> getProductOptionList(
            @PathVariable String productCode) {
        return ApiResponse.ok("상세 상품 옵션이 조회되었습니다.",
                productDetailService.getProductOptionList(productCode).toVo()
        );
    }
}
