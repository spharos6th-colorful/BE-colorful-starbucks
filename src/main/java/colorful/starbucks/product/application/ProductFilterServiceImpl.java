package colorful.starbucks.product.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.product.domain.ProductFilter;
import colorful.starbucks.product.dto.ProductFilterDto;
import colorful.starbucks.product.dto.request.ProductFilterCreateRequestDto;
import colorful.starbucks.product.dto.response.ProductCursorResponseDto;
import colorful.starbucks.product.infrastructure.ProductFilterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductFilterServiceImpl implements ProductFilterService {

    private final ProductFilterRepository productFilterRepository;

    @Transactional
    @Override
    public void createProductCategoryList(ProductFilterCreateRequestDto productFilterCreateRequestDto) {

        productFilterRepository.save(
                productFilterCreateRequestDto.toEntity()
        );
    }

    @Override
    public CursorPage<ProductCursorResponseDto> getFilteredProductList(ProductFilterDto productFilterDto) {

        Long id;
        int price;

        if (productFilterDto.getCursor() == null) {
            id = productFilterDto.getSortBy().equals("createdAt,asc") ? 0L : Long.MAX_VALUE;
            price = productFilterDto.getSortBy().equals("price,asc") ? 0 : Integer.MAX_VALUE;
        } else {
            ProductFilter productFilter = productFilterRepository.findById(productFilterDto.getCursor())
                    .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
            id = productFilter.getId();
            price = productFilter.getPrice();
        }

        return productFilterRepository.getFilteredProductList(productFilterDto, id, price);
    }
}
