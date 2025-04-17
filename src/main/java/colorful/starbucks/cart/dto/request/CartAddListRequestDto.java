package colorful.starbucks.cart.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CartAddListRequestDto {

    private List<CartAddRequestDto> products;

}
