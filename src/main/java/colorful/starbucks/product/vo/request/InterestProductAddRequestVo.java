package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class InterestProductAddRequestVo {

    private Long productCode;
    private String productName;
    private Integer price;
    private String productThumbnailUrl;
}
