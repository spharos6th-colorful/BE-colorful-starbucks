package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.InterestProductService;
import colorful.starbucks.product.application.ProductService;
import colorful.starbucks.product.dto.request.InterestProductCreateRequestDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.vo.request.InterestProductCreateRequestVo;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import colorful.starbucks.product.vo.response.InterestProductCreateResponseVo;
import colorful.starbucks.product.vo.response.ProductCreateResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final InterestProductService interestProductService;

    @PostMapping
    public ApiResponse<ProductCreateResponseVo> createProduct(@RequestPart ProductCreateRequestVo productCreateRequestVo,
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

    // security 완료되면 memberUuid는 authentication에서 가져오도록 수정
    @PostMapping("/interest/{memberUuid}")
    public ApiResponse<InterestProductCreateResponseVo> createInterestProduct(
            @RequestBody InterestProductCreateRequestVo interestProductCreateRequestVo,
            @PathVariable String memberUuid) {

        return ApiResponse.of(
                HttpStatus.CREATED,
                "관심 상품 등록을 완료했습니다." ,
                interestProductService.createInterestProduct(
                        InterestProductCreateRequestDto.from(interestProductCreateRequestVo, memberUuid))
                        .toVo()
        );
    }
}
