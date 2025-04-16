package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.application.ProductService;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;
import colorful.starbucks.product.vo.ProductFilterVo;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import colorful.starbucks.product.vo.response.ProductCursorResponseVo;
import colorful.starbucks.product.vo.response.ProductOptionListResponseVo;
import colorful.starbucks.product.vo.response.ProductResponseVo;
import colorful.starbucks.product.vo.response.ProductSimpleResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // todo: 프론트에서 s3 업로드 구현 완료되면 file이 아닌 image url을 받는 것으로 수정할 것
    @Operation(
            summary = "상품 등록 API",
            description = "상품 등록 API 입니다.",
            tags = {"PRODUCT-SERVICE"}
    )
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

    @Operation(
            summary = "상품 목록 조회 API",
            description = "필터링, 정렬 조건을 포함한 상품 조회 API 입니다.",
            tags = {"PRODUCT-SERVICE"}
    )
    @GetMapping
    public ApiResponse<CursorPage<ProductCursorResponseVo>> getProductsByFilter(@ModelAttribute ProductFilterVo productFilterVo) {
        return ApiResponse.ok("상품 목록 조회를 완료했습니다.",
                productService.getProductsByFilter(ProductFilterDto.from(productFilterVo))
                        .map(ProductCursorResponseDto::toVo)
        );
    }

    @Operation(
            summary = "상품 조회 API",
            description = "상품 코드로 상품에 대한 정보를 조회하는 API 입니다.",
            tags = {"PRODUCT-SERVICE"}
    )
    @GetMapping("/{productCode}")
    public ApiResponse<ProductResponseVo> getProduct(@PathVariable Long productCode) {
        return ApiResponse.ok(
                "상품 조회를 완료했습니다.",
                productService.getProduct(productCode).toVo()
        );
    }

    @Operation(
            summary = "상품 옵션 조회 API",
            description = "상품 코드로 상품 옵션을 조회하는 API 입니다.",
            tags = {"PRODUCT-SERVICE"}
    )
    @GetMapping("/{productCode}/options")
    public ApiResponse<ProductOptionListResponseVo> getProductOptionList(@PathVariable Long productCode) {
        return ApiResponse.ok("상세 상품 옵션이 조회되었습니다.",
                productService.getProductOptionList(productCode).toVo()
        );
    }

    @Operation(
            summary = "상품 간단 조회 API",
            description = "상품 코드로 상품 간단 정보를 조회하는 API 입니다.",
            tags = {"PRODUCT-SERVICE"}
    )
    @GetMapping("/{productCode}/simple")
    public ApiResponse<ProductSimpleResponseVo> getProductSimpleInformation(@PathVariable Long productCode) {
        return ApiResponse.ok(
                "상품 조회를 완료했습니다.",
                productService.getProductSimpleInformation(productCode).toVo()
        );
    }
}
