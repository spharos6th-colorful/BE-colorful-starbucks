package colorful.starbucks.product.vo.request;

import lombok.Getter;

@Getter
public class InterestProductAddRequestVo {

    private String productCode;
    private String productName;
    private int price;
    private String productThumbnailUrl;
}
