package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductDetailService;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.vo.request.ProductDetailCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @PostMapping("/products/{productCode}/details")
    public ApiResponse<Void> createProductDetail(
            @PathVariable("productCode") String productCode,
            @RequestPart ProductDetailCreateRequestVo request,
            @RequestPart MultipartFile productDetailThumbnail) {

        System.out.println("productCode = " + productCode);
        productDetailService.createProductDetail(productCode,
                ProductDetailCreateRequestDto.from(request),
                productDetailThumbnail);

        return ApiResponse.ok("상세 상품이 등록되었습니다.", null);
    }
}
