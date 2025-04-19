package colorful.starbucks.order.application;

import colorful.starbucks.cart.application.CartService;
import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.common.util.OrderCodeGenerator;
import colorful.starbucks.delivery.domain.DeliveryAddress;
import colorful.starbucks.delivery.infrastructure.DeliveryRepository;
import colorful.starbucks.order.domain.Order;
import colorful.starbucks.order.domain.OrderStatus;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.PreOrderDto;
import colorful.starbucks.order.dto.request.OrderCancelRequestDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.request.OrderDetailCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCreateResponseDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.infrastructure.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final OrderCodeGenerator orderCodeGenerator;
    private final OrderRedisService orderRedisService;
    private final CartService cartService;
    private final DeliveryRepository deliveryRepository;



    @Transactional
    @Override
    public PreOrderDto createPreOrder(OrderCreateRequestDto orderCreateRequestDto) {
        Long orderCode = orderCodeGenerator.generate();

        PreOrderDto preOrderDto = PreOrderDto.from(orderCreateRequestDto, orderCode);

        orderRedisService.saveOrder(orderCode, preOrderDto, 3600);


        return preOrderDto;
    }

    @Transactional
    @Override
    public OrderCreateResponseDto createOrder(OrderCreateRequestDto orderCreateRequestDto) {
        Long orderCode = orderCreateRequestDto.getOrderCode();

        DeliveryAddress deliveryAddress = deliveryRepository.findById(orderCreateRequestDto.getDeliveryAddressId())
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_SHIPPING_ADDRESS));


        Order order = orderCreateRequestDto.toEntity(
                orderCode,
                deliveryAddress.getZoneCode(),
                deliveryAddress.getAddress(),
                deliveryAddress.getDetailAddress()
        );

        orderDetailService.saveAllDetails(order, orderCreateRequestDto.getOrderDetails());

        List<Long> cartIds = orderCreateRequestDto.getOrderDetails().stream()
                .map(OrderDetailCreateRequestDto::getProductDetailCode)
                .toList();

        cartService.removeCartByMemberAndProductDetailCodes(
                orderCreateRequestDto.getMemberUuid(),
                cartIds
        );

        return OrderCreateResponseDto.from(orderCode);
    }


    @Override
    public CursorPage<OrderCursorResponseDto> getOrderList(OrderListFilterDto orderListFilterDto) {
        return orderRepository.getOrderList(orderListFilterDto)
                .map(OrderCursorResponseDto::from);
    }

    @Transactional
    @Override
    public OrderCancelRequestDto cancelOrder(OrderCancelRequestDto orderCancelRequestDto) {


        Order order = orderRepository.findByOrderCode(orderCancelRequestDto.getOrderCode())
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_ORDER));

        if (order.getOrderStatus() == OrderStatus.CANCELLED) {
            throw new BaseException(ResponseStatus.CANCELLED_ORDER);
        }

        cancelOrderUpdate(orderCancelRequestDto, order.getMemberUuid());

        return orderCancelRequestDto;
    }

    private void cancelOrderUpdate(OrderCancelRequestDto orderCancelRequestDto, String memberUuid) {
        Order order = orderRepository.findByOrderCode(orderCancelRequestDto.getOrderCode())
                .orElseThrow(() -> new BaseException(ResponseStatus.NO_EXIST_ORDER));

        Order updatedOrder = Order.builder()
                .id(order.getId())
                .orderCode(orderCancelRequestDto.getOrderCode())
                .couponUuid(order.getCouponUuid())
                .couponName(order.getCouponName())
                .zoneCode(order.getZoneCode())
                .address(order.getAddress())
                .detailAddress(order.getDetailAddress())
                .totalAmount(order.getTotalAmount())
                .discountAmount(order.getDiscountAmount())
                .buyer(order.getBuyer())
                .orderCancelReason(orderCancelRequestDto.getOrderCancelReason())
                .orderCancelReasonDetail(orderCancelRequestDto.getOrderCancelReasonDetail())
                .memberUuid(memberUuid)
                .orderStatus(OrderStatus.CANCELLED)
                .build();

        orderRepository.save(updatedOrder);
    }


}



