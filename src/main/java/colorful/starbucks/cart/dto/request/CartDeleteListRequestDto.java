package colorful.starbucks.cart.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class CartDeleteListRequestDto {

    private List<CartDeleteRequestDto> cartIds;
}
