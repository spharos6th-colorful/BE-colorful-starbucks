package colorful.starbucks.product.application;

import colorful.starbucks.admin.dto.ProductTopCategoryDto;
import colorful.starbucks.admin.dto.ProductTopCategoryDtos;
import colorful.starbucks.admin.vo.ProductTopCategoryVo;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.product.dto.response.BestProductsResponseDtos;
import colorful.starbucks.product.infrastructure.BestProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BestProductServiceImpl implements BestProductService {

    private final BestProductRepository bestProductRepository;

    @Override
    public BestProductsResponseDtos getBestProductByCategoryId(Long categoryId) {
        return BestProductsResponseDtos.from(
                bestProductRepository.findAllByCategoryIdOrderByRankingAsc(categoryId));
    }

    @Override
    public ProductTopCategoryDtos getBestProductCategories() {
        return ProductTopCategoryDtos.of(bestProductRepository.findAll().stream()
                .map(product -> ProductTopCategoryDto.builder()
                        .topCategoryId(product.getCategoryId())
                        .categoryName(product.getCategoryName())
                        .build())
                .distinct()
                .toList());
    }
}
