package colorful.starbucks.order.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.common.util.OrderCodeGenerator;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final OrderCodeGenerator orderCodeGenerator;

    @Transactional
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        Long orderCode = orderCodeGenerator.generate();

        Order order = orderCreateRequestDto.toEntity(orderCode);
        orderRepository.save(order);

        orderDetailService.saveAllDetails(order, orderCreateRequestDto.getOrderDetails());
        return OrderCreateResponseDto.from(orderCode);
    }

    @Transactional
    @Override
    public CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto) {
        return orderRepository.getOrderList(orderListFilterDto);
    }



}
