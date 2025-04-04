package colorful.starbucks.order.application;

import colorful.starbucks.common.util.OrderCodeGenerator;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderDetail;
import colorful.starbucks.order.dto.in.OrderCreateRequestDto;
import colorful.starbucks.order.dto.out.OrderCreateResponseDto;
import colorful.starbucks.order.infrastructure.OrderDetailRepository;
import colorful.starbucks.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderCodeGenerator orderCodeGenerator;

    @Transactional
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        Long orderCode = orderCodeGenerator.generate();

        Order order = orderCreateRequestDto.toEntity(orderCode);
        orderRepository.save(order);

        List<OrderDetail> orderDetails = orderCreateRequestDto.getOrderDetails().stream()
                .map(orderDetailRequestDto -> orderDetailRequestDto.toEntity(order))
                .collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);

        return OrderCreateResponseDto.builder()
                .orderCode(String.valueOf(orderCode))
                .build();




    }

}
