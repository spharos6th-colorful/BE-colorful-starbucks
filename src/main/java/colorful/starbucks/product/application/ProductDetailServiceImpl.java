package colorful.starbucks.product.application;

import colorful.starbucks.common.s3.S3UploadService;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductDetailCreateResponseDto;
import colorful.starbucks.product.generator.ProductDetailCodeGenerator;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final S3UploadService s3UploadService;
    private final ProductDetailCodeGenerator productDetailCodeGenerator;

    @Transactional
    @Override
    public ProductDetailCreateResponseDto createProductDetail(String productCode,
                                                              ProductDetailCreateRequestDto productDetailCreateRequestDto,
                                                              MultipartFile productDetailThumbnail) {

        try {
            String productDetailThumbnailUrl = s3UploadService.uploadFile(productDetailThumbnail);
            return ProductDetailCreateResponseDto.from(productDetailRepository.save(
                    productDetailCreateRequestDto.toEntity(productCode,
                            productDetailCodeGenerator.generate(),
                            productDetailThumbnailUrl
                    )
            ));
        } catch (Exception e) {
            throw new RuntimeException("상품 상세 등록에 실패했습니다.");
        }
    }
}
