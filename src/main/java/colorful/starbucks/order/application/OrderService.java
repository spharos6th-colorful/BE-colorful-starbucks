package colorful.starbucks.order.application;

import colorful.starbucks.order.dto.in.OrderCreateRequestDto;
import colorful.starbucks.order.dto.out.OrderCreateResponseDto;

public interface OrderService {

    OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto);
}
