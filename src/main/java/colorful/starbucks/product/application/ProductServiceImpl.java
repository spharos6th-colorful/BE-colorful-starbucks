package colorful.starbucks.product.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.s3.S3UploadService;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;
import colorful.starbucks.product.dto.response.ProductOptionListResponseDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import colorful.starbucks.product.dto.response.ProductSimpleResponseDto;
import colorful.starbucks.product.generator.ProductCodeGenerator;
import colorful.starbucks.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final S3UploadService s3UploadService;
    private final ProductCodeGenerator productCodeGenerator;
    private final ProductFilterService productFilterService;
    private final ProductDetailService productDetailService;

    @Transactional
    @Override
    public ProductResponseDto createProduct(ProductCreateRequestDto productCreateRequestDto,
                                            MultipartFile productThumbnail,
                                            MultipartFile productImage) {

        try {
            String productThumbnailUrl = s3UploadService.uploadFile(productThumbnail);
            String productImageUrl = s3UploadService.uploadFile(productImage);
            Product product = productRepository.save(
                    productCreateRequestDto.toEntity(
                            productCodeGenerator.generate(),
                            productThumbnailUrl,
                            productImageUrl)
            );
            product.initProductCode();

            return ProductResponseDto.from(product);
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.REQUEST_CONFLICT, "상품 등록에 실패했습니다.");
        }
    }

    @Override
    public CursorPage<ProductCursorResponseDto> getProductsByFilter(ProductFilterDto productFilterDto) {
        return productFilterService.getFilteredProductList(productFilterDto);
    }

    @Override
    public ProductResponseDto getProduct(Long productCode) {
        return ProductResponseDto.from(
                productRepository.findByProductCodeAndIsDeletedIsFalse(productCode).
                        orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
        );
    }

    @Override
    public ProductOptionListResponseDto getProductOptionList(Long productCode) {
        return productDetailService.getProductOptionList(productCode);
    }

    @Override
    public ProductSimpleResponseDto getProductSimpleInformation(Long productCode) {
        return ProductSimpleResponseDto.from(
                productRepository.findByProductCodeAndIsDeletedIsFalse(productCode)
                        .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
        );
    }
}
