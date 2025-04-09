package colorful.starbucks.order.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.application.OrderService;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.vo.OrderListFilterVo;
import colorful.starbucks.order.vo.request.OrderCreateRequestVo;
import colorful.starbucks.order.vo.response.OrderCreateResponseVo;
import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderCreateResponseVo> createOrder(Authentication authentication,
                                                          @RequestBody OrderCreateRequestVo orderCreateRequestVo) {
        return ApiResponse.ok(
                "주문이 완료되었습니다.",
                orderService
                        .createOrder(OrderCreateRequestDto.of(orderCreateRequestVo, authentication.getName()))
                        .toVo()
        );
    }

    @GetMapping
    public ApiResponse<CursorPage<OrderCursorResponseVo>> getOrderList(Authentication authentication,
                                                                       @ModelAttribute OrderListFilterVo orderListFilterVo) {
        String memberUuid = authentication.getName();

        OrderListFilterDto orderListFilterDto = OrderListFilterDto.of(orderListFilterVo, memberUuid);

        CursorPage<OrderCursorResponseDto> response = orderService.getOrderList(orderListFilterDto);

        return ApiResponse.ok("주문 목록 조회 성공",
                CursorPage.<OrderCursorResponseVo>builder()
                        .content(response.getContent().stream()
                                .map(OrderCursorResponseDto::toVo)
                                .toList())
                        .nextCursor(response.getNextCursor())
                        .hasNext(response.getHasNext())
                        .build()
        );
    }



}

