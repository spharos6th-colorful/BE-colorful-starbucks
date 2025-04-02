package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.infrastructure.ProductCategoryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductCategoryListRepository productCategoryListRepository;

    @Transactional
    @Override
    public void createProductCategoryList(ProductCategoryListCreateRequestDto productCategoryListCreateRequestDto) {

        productCategoryListRepository.save(
                productCategoryListCreateRequestDto.toEntity()
        );
    }
}
