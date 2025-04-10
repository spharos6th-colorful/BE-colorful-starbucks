package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.application.ProductService;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import colorful.starbucks.product.vo.ProductFilterVo;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import colorful.starbucks.product.vo.response.ProductResponseVo;
import colorful.starbucks.product.vo.response.ProductSimpleResponseVo;
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

    // todo: 프론트에서 s3 업로드 구현 완료되면 file이 아닌 image url을 받는 것으로 수정할 것
    @PostMapping
    public ApiResponse<ProductResponseVo> createProduct(@RequestPart ProductCreateRequestVo productCreateRequestVo,
                                                        @RequestPart(required = false) MultipartFile productThumbnail,
                                                        @RequestPart(required = false) MultipartFile productImage) {
        return ApiResponse.of(
                HttpStatus.CREATED,
                "상품 등록을 완료했습니다.",
                productService.createProduct(
                                ProductCreateRequestDto.from(productCreateRequestVo), productThumbnail, productImage).toVo()
        );
    }

    @GetMapping
    public ApiResponse<CursorPage<ProductResponseVo>> getProductsByFilter(@ModelAttribute ProductFilterVo productFilterVo,
                                                                          @PageableDefault(size = 3) Pageable pageable) {

        return ApiResponse.ok(
                "상품 목록 조회를 완료했습니다.",
                productService.getProductsByFilter(ProductFilterDto.from(productFilterVo), pageable)
                        .map(ProductResponseDto::toVo)
        );
    }

    @GetMapping("/{productCode}")
    public ApiResponse<ProductResponseVo> getProduct(@PathVariable Long productCode) {
        return ApiResponse.ok(
                "상품 조회를 완료했습니다.",
                productService.getProduct(productCode).toVo()
        );
    }

    @GetMapping("/{productCode}/simple")
    public ApiResponse<ProductSimpleResponseVo> getProductSimpleInformation(@PathVariable Long productCode) {
        return ApiResponse.ok(
                "상품 조회를 완료했습니다.",
                productService.getProductSimpleInformation(productCode).toVo()
        );
    }
}
