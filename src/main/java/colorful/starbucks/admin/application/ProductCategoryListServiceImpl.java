package colorful.starbucks.admin.application;

import colorful.starbucks.admin.domain.ProductCategoryList;
import colorful.starbucks.admin.dto.ProductCategoryListFilterDto;
import colorful.starbucks.admin.dto.ProductSearchListFilterDto;
import colorful.starbucks.admin.dto.request.ProductCategoryListCreateRequestDto;
import colorful.starbucks.admin.dto.response.ProductCategoryCursorResponseDto;
import colorful.starbucks.admin.dto.response.ProductSearchCursorResponseDto;
import colorful.starbucks.admin.infrastructure.ProductReadRepository;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductCategoryListServiceImpl implements ProductCategoryListService {

    private final ProductReadRepository productReadRepository;

    @Transactional
    @Override
    public void createProductCategoryList(ProductCategoryListCreateRequestDto productCategoryListCreateRequestDto) {

        productReadRepository.save(
                productCategoryListCreateRequestDto.toEntity()
        );
    }

    @Override
    public CursorPage<ProductCategoryCursorResponseDto> getFilteredProductList(ProductCategoryListFilterDto productCategoryListFilterDto) {

        Long id;
        int price;

        if (productCategoryListFilterDto.getCursor() == null) {
            id = productCategoryListFilterDto.getSortBy().equals("createdAt,asc") ? 0L : Long.MAX_VALUE;
            price = productCategoryListFilterDto.getSortBy().equals("price,asc") ? 0 : Integer.MAX_VALUE;
        } else {
            ProductCategoryList productCategoryList = productReadRepository.findById(productCategoryListFilterDto.getCursor())
                    .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));
            id = productCategoryList.getId();
            price = productCategoryList.getPrice();
        }

        return productReadRepository.getFilteredProductList(productCategoryListFilterDto, id, price);
    }

    @Override
    public CursorPage<ProductSearchCursorResponseDto> getSearchedProductList(ProductSearchListFilterDto productSearchListFilterDto) {
        return productReadRepository.getSearchedProductList(productSearchListFilterDto);
    }
}
