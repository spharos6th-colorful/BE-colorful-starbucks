package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class InterestProductCreateRequestVo {

    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;
}
