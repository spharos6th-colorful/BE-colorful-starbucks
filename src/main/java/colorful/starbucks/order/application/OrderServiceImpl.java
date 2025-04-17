package colorful.starbucks.order.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.common.util.OrderCodeGenerator;
import colorful.starbucks.member.domain.Member;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderStatus;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.request.OrderCancelRequestDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.infrastructure.OrderRepository;
import colorful.starbucks.summary.application.MemberOrderSummaryService;
import colorful.starbucks.summary.domain.MemberOrderSummary;
import colorful.starbucks.summary.infrastructure.MemberOrderSummaryRepository;
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
    private final MemberOrderSummaryService memberOrderSummaryService;

    @Transactional
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto, Member member) {
        Long orderCode = orderCodeGenerator.generate();

        Order order = orderCreateRequestDto.toEntity(orderCode, member.getMemberUuid());
        orderRepository.save(order);

        orderDetailService.saveAllDetails(order, orderCreateRequestDto.getOrderDetails());

        return OrderCreateResponseDto.from(orderCode);
    }


    @Override
    public CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto) {
        return orderRepository.getOrderList(orderListFilterDto);
    }

    @Transactional
    @Override
    public OrderCancelRequestDto cancelOrder(OrderCancelRequestDto orderCancelRequestDto) {


        Order order = orderRepository.findByOrderCode(orderCancelRequestDto.getOrderCode())
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_ORDER));

        if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new BaseException(ResponseStatus.CANCELLED_ORDER);
        }

        order.cancel(
                orderCancelRequestDto.getOrderCancelReason(),
                orderCancelRequestDto.getOrderCancelReasonDetail()
        );

        return orderCancelRequestDto;
    }


}
