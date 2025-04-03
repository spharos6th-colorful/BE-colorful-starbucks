package colorful.starbucks.product.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.s3.S3UploadService;
import colorful.starbucks.product.domain.ProductDetail;
import colorful.starbucks.product.dto.request.ProductDetailCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductDetailCodeAndQuantityResponseDto;
import colorful.starbucks.product.dto.response.ProductDetailResponseDto;
import colorful.starbucks.product.dto.response.ProductOptionListResponseDto;
import colorful.starbucks.product.generator.ProductDetailCodeGenerator;
import colorful.starbucks.product.infrastructure.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final S3UploadService s3UploadService;
    private final ProductDetailCodeGenerator productDetailCodeGenerator;

    @Transactional
    @Override
    public ProductDetailResponseDto createProductDetail(ProductDetailCreateRequestDto productDetailCreateRequestDto,
                                                        MultipartFile productDetailThumbnail) {

        if (productDetailRepository.existsByProductCodeAndSizeIdAndColorIdAndIsDeletedFalse(
                productDetailCreateRequestDto.getProductCode(),
                productDetailCreateRequestDto.getSizeId(),
                productDetailCreateRequestDto.getColorId())) {
            throw new BaseException(ResponseStatus.REQUEST_CONFLICT, "이미 등록된 상품입니다.");
        }

        try {
            String productDetailThumbnailUrl = s3UploadService.uploadFile(productDetailThumbnail);
            return ProductDetailResponseDto.from(productDetailRepository.save(
                    productDetailCreateRequestDto.toEntity(
                            productDetailCodeGenerator.generate(),
                            productDetailThumbnailUrl
                    )
            ));
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.REQUEST_CONFLICT, "상세 상품 등록에 실패했습니다.");
        }
    }

    @Override
    public ProductDetailResponseDto getProductDetail(String productDetailCode) {
        return ProductDetailResponseDto.from(
                productDetailRepository.findByProductDetailCode(productDetailCode)
                        .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
        );
    }

    @Override
    public ProductOptionListResponseDto getProductOptionList(String productCode) {
        List<ProductDetail> productDetails = productDetailRepository.findAllByProductCode(productCode);
        return ProductOptionListResponseDto.from(productDetails);
    }

    @Override
    public ProductDetailCodeAndQuantityResponseDto getProductDetailWithOptions(
            String productCode,
            Long sizeId,
            Long colorId) {

        return ProductDetailCodeAndQuantityResponseDto.from(
                productDetailRepository.findByProductCodeAndOptions(productCode, sizeId, colorId)
                        .orElseThrow(() ->  new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
        );
    }

}
