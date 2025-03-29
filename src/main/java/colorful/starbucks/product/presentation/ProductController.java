package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductService;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.vo.ProductFilterVo;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import colorful.starbucks.product.vo.response.FilteredProductResponseVo;
import colorful.starbucks.product.vo.response.ProductResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponseVo> createProduct(@RequestPart ProductCreateRequestVo productCreateRequestVo,
                                                        @RequestPart MultipartFile productThumbnail,
                                                        @RequestPart MultipartFile productCommonImage) {
        return ApiResponse.of(
                HttpStatus.CREATED,
                "상품 등록을 완료했습니다." ,
                productService.create(
                        ProductCreateRequestDto.from(productCreateRequestVo), productThumbnail, productCommonImage)
                        .toVo()
        );
    }

    @GetMapping
    public ApiResponse<FilteredProductResponseVo> getProductsByFilter(ProductFilterVo productFilterVo,
                                                                      @PageableDefault(size = 3) Pageable pageable) {

        return ApiResponse.ok(
                "상품 목록 조회를 완료했습니다.",
                productService.getProductsByFilter(ProductFilterDto.from(productFilterVo), pageable).toVo()
        );
    }

    @GetMapping("/{productCode}")
    public ApiResponse<ProductResponseVo> getProduct(@PathVariable String productCode) {
        return ApiResponse.ok(
                "상품 조회를 완료했습니다.",
                productService.getProduct(productCode).toVo()
        );
    }
}
