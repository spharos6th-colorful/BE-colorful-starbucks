package colorful.starbucks.product.application;

import colorful.starbucks.common.s3.S3UploadService;
import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.FilteredProductResponseDto;
import colorful.starbucks.product.dto.response.ProductResponseDto;
import colorful.starbucks.product.generator.ProductCodeGenerator;
import colorful.starbucks.product.infrastructure.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final S3UploadService s3UploadService;
    private final ProductCodeGenerator productCodeGenerator;

    @Transactional
    @Override
    public ProductResponseDto create(ProductCreateRequestDto productCreateRequestDto,
                                     MultipartFile productThumbnail,
                                     MultipartFile productCommonImage) {

        String productCode = productCodeGenerator.generate();

        try {
            String productThumbnailUrl = s3UploadService.uploadFile(productThumbnail);
            String productCommonImageUrl = s3UploadService.uploadFile(productCommonImage);
            return ProductResponseDto.from(productRepository.save(
                    productCreateRequestDto.toEntity(
                            productCode,
                            productThumbnailUrl,
                            productCommonImageUrl)
            ));
        } catch (Exception e) {
            throw new RuntimeException("상품 등록에 실패했습니다.");
        }
    }

    @Override
    public FilteredProductResponseDto getProductsByFilter(ProductFilterDto productFilterDto, Pageable pageable) {

        Long productId;
        int price;

        if (productFilterDto.getCursorProductCode() == null) {
            productId = productFilterDto.getSortBy().equals("createdAt,asc") ? 0L : Long.MAX_VALUE;
            price = productFilterDto.getSortBy().equals("price,asc") ? 0 : Integer.MAX_VALUE;
        } else {
            Product product = productRepository.findByProductCode(productFilterDto.getCursorProductCode())
                    .orElseThrow(() -> new RuntimeException("상품 조회에 실패했습니다."));
            productId = product.getId();
            price = product.getPrice();
        }

        return productRepository.getProductsByFilter(productFilterDto, productId, price, pageable);
    }

    @Override
    public ProductResponseDto getProduct(String productCode) {
        return ProductResponseDto.from(
                productRepository.findByProductCode(productCode).
                        orElseThrow(() -> new RuntimeException("상품 조회에 실패했습니다.")
        ));
    }
}
