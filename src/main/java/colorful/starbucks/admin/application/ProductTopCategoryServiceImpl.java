package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.ProductTopCategoryDtos;
import colorful.starbucks.admin.dto.request.ProductTopCategoryCreateRequestDto;
import colorful.starbucks.admin.infrastructure.ProductTopCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductTopCategoryServiceImpl implements ProductTopCategoryService {

    private final ProductTopCategoryRepository productTopCategoryRepository;

    @Transactional
    @Override
    public void createTopCategory(ProductTopCategoryCreateRequestDto productTopCategoryCreateRequestDto) {

        productTopCategoryRepository.save(
                productTopCategoryCreateRequestDto.toEntity()
        );
    }

    @Override
    public ProductTopCategoryDtos getTopCategories(Pageable pageable) {
        return ProductTopCategoryDtos.from(productTopCategoryRepository.findAll(pageable));
    }
}
