package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.request.ProductBottomCategoryCreateRequestDto;
import colorful.starbucks.admin.infrastructure.ProductBottomCategoryRepository;
import colorful.starbucks.admin.infrastructure.ProductTopCategoryRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductBottomCategoryServiceImpl implements ProductBottomCategoryService {

    private final ProductBottomCategoryRepository productBottomCategoryRepository;
    private final ProductTopCategoryRepository productTopCategoryRepository;

    @Transactional
    @Override
    public void createBottomCategory(ProductBottomCategoryCreateRequestDto productBottomCategoryCreateRequestDto) {

        try {
            productBottomCategoryRepository.save(
                    productBottomCategoryCreateRequestDto.toEntity(
                            productTopCategoryRepository.findById(productBottomCategoryCreateRequestDto.getTopCategoryId())
                                    .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
                    )
            );
        } catch (Exception e) {
            throw new BaseException(ResponseStatus.CONFLICT_REQUEST, "하위 카테고리 등록에 실패했습니다.");
        }

    }
}
