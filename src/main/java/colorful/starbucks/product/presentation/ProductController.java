package colorful.starbucks.product.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.product.application.ProductService;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.vo.request.ProductCreateRequestVo;
import colorful.starbucks.product.vo.response.ProductCreateResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ApiResponse<ProductCreateResponseVo> createProduct(@RequestPart ProductCreateRequestVo request,
                                                              @RequestPart MultipartFile productThumbnail,
                                                              @RequestPart MultipartFile productCommonImage) {
        return ApiResponse.of(
                HttpStatus.CREATED,
                "상품 등록을 완료했습니다." ,
                productService.create(ProductCreateRequestDto.from(request), productThumbnail, productCommonImage).toVo()
        );
    }
}
