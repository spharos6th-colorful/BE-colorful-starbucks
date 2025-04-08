package colorful.starbucks.admin.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class IdAndPriceDto {

    private Long id;
    private int price;

    @Builder
    public IdAndPriceDto(Long id, int price) {
        this.id = id;
        this.price = price;
    }
}
