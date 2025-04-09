package colorful.starbucks.admin.dto.request;

import colorful.starbucks.admin.domain.ProductCategoryList;
import colorful.starbucks.admin.vo.request.ProductCategoryListCreateRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductCategoryListCreateRequestDto {
    private Long productCode;
    private Long topCategoryId;
    private String topCategoryName;
    private Long bottomCategoryId;
    private String bottomCategoryName;
    private Integer price;

    @Builder
    private ProductCategoryListCreateRequestDto(Long productCode,
                                                Long topCategoryId,
                                                String topCategoryName,
                                                Long bottomCategoryId,
                                                String bottomCategoryName,
                                                Integer price) {
        this.productCode = productCode;
        this.topCategoryId = topCategoryId;
        this.topCategoryName = topCategoryName;
        this.bottomCategoryId = bottomCategoryId;
        this.bottomCategoryName = bottomCategoryName;
        this.price = price;
    }

    public static ProductCategoryListCreateRequestDto from(
            ProductCategoryListCreateRequestVo productCategoryListCreateRequestVo
    ) {
        return ProductCategoryListCreateRequestDto.builder()
                .productCode(productCategoryListCreateRequestVo.getProductCode())
                .topCategoryId(productCategoryListCreateRequestVo.getTopCategoryId())
                .topCategoryName(productCategoryListCreateRequestVo.getTopCategoryName())
                .bottomCategoryId(productCategoryListCreateRequestVo.getBottomCategoryId())
                .bottomCategoryName(productCategoryListCreateRequestVo.getBottomCategoryName())
                .price(productCategoryListCreateRequestVo.getPrice())
                .build();
    }

    public ProductCategoryList toEntity() {
        return ProductCategoryList.builder()
                .productCode(productCode)
                .topCategoryId(topCategoryId)
                .topCategoryName(topCategoryName)
                .bottomCategoryId(bottomCategoryId)
                .bottomCategoryName(bottomCategoryName)
                .price(price)
                .build();
    }

}
