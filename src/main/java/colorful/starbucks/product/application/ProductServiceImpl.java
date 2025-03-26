package colorful.starbucks.product.application;

import colorful.starbucks.auth.domain.Member;
import colorful.starbucks.common.s3.S3UploadService;
import colorful.starbucks.product.domain.Product;
import colorful.starbucks.product.dto.request.ProductCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCreateResponseDto;
import colorful.starbucks.product.generator.ProductCodeGenerator;
import colorful.starbucks.product.infrastructure.ProductRepository;
import colorful.starbucks.product.vo.ProductCreateRequestVo;
import lombok.RequiredArgsConstructor;
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
    public ProductCreateResponseDto create(ProductCreateRequestVo request,
                                           MultipartFile productThumbnail,
                                           MultipartFile productCommonImage) {
        ProductCreateRequestDto productCreateRequestDto = ProductCreateRequestDto.from(request);
        String productCode = productCodeGenerator.generate();

        try {
            String productThumbnailUrl = s3UploadService.uploadFile(productThumbnail);
            String productCommonImageUrl = s3UploadService.uploadFile(productCommonImage);
            Product savedProduct = productRepository.save(
                    productCreateRequestDto.toEntity(
                            productCode,
                            productThumbnailUrl,
                            productCommonImageUrl)
            );
            return ProductCreateResponseDto.from(savedProduct);
        } catch (Exception e) {
            throw new RuntimeException("상품 등록에 실패했습니다.");
        }
    }
}
