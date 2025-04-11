package colorful.starbucks.admin.application;

import colorful.starbucks.admin.dto.request.ProductColorAddRequestDto;
import colorful.starbucks.admin.dto.request.ProductSizeAddRequestDto;
import colorful.starbucks.admin.infrastructure.ProductColorRepository;
import colorful.starbucks.admin.infrastructure.ProductSizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductOptionServiceImpl implements ProductOptionService {

    private final ProductColorRepository productColorRepository;
    private final ProductSizeRepository productSizeRepository;

    @Transactional
    @Override
    public void createColorOption(ProductColorAddRequestDto productColorAddRequestDto) {
        productColorRepository.save(productColorAddRequestDto.toEntity());
    }

    @Transactional
    @Override
    public void createSizeOption(ProductSizeAddRequestDto productSizeAddRequestDto) {
        productSizeRepository.save(productSizeAddRequestDto.toEntity());
    }
}
