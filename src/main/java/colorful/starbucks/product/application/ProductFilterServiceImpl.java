package colorful.starbucks.product.application;

import colorful.starbucks.admin.dto.ProductIdAndPriceDto;
import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
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

    @Override
    public CursorPage<ProductCursorResponseDto> getSearchedProductList(ProductSearchListFilterDto productSearchListFilterDto) {
        Long id;
        int price;

        String sortBy = productSearchListFilterDto.getSortBy() == null?"createdAt,desc":productSearchListFilterDto.getSortBy();

        if (productSearchListFilterDto.getCursor() == null) {
            id = sortBy.equals("createdAt,asc") ? 0L : Long.MAX_VALUE;
            price = sortBy.equals("price,asc") ? 0 : Integer.MAX_VALUE;
        }else{
            id = productSearchListFilterDto.getCursor();
            ProductFilter productFilter = productFilterRepository.findById(id).orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
            price = productFilter.getPrice();
        }

        return productFilterRepository.getSearchedProductList(ProductIdAndPriceDto.of(productSearchListFilterDto, id, price));
    }
}
