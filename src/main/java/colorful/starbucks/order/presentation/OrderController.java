package colorful.starbucks.order.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.application.OrderService;
import colorful.starbucks.order.dto.OrderListFilterDto;
import colorful.starbucks.order.dto.request.OrderCancelRequestDto;
import colorful.starbucks.order.dto.request.OrderCreateRequestDto;
import colorful.starbucks.order.dto.request.OrderExistsRequestDto;
import colorful.starbucks.order.dto.request.PreOrderRequestDto;
import colorful.starbucks.order.dto.response.OrderCursorResponseDto;
import colorful.starbucks.order.vo.OrderListFilterVo;
import colorful.starbucks.order.vo.request.OrderCancelRequestVo;
import colorful.starbucks.order.vo.request.OrderCreateRequestVo;
import colorful.starbucks.order.vo.request.OrderExistsResponseVo;
import colorful.starbucks.order.vo.request.PreOrderRequestVo;
import colorful.starbucks.order.vo.response.OrderCreateResponseVo;
import colorful.starbucks.order.vo.response.OrderCursorResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @Operation(
            summary = "주문 생성 전 API",
            description = "주문 생성을 위한 사전 작업을 수행하는 API 입니다.",
            tags = {"ORDER-SERVICE"}
    )
    @PostMapping("/pre")
    public ApiResponse<OrderCreateResponseVo> createPreOrder(Authentication authentication,
                                                             @RequestBody PreOrderRequestVo preOrderRequestVo) {
        return ApiResponse.ok(
                "주문 생성 전 작업이 완료되었습니다.",
                orderService.createPreOrder(PreOrderRequestDto.of(preOrderRequestVo, authentication.getName()))
                        .toVo()
        );
    }

    @Operation(
            summary = "주문 생성 API",
            description = "주문을 생성하는 API 입니다.",
            tags = {"ORDER-SERVICE"}
    )
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

    @Operation(
            summary = "주문 목록 조회 API",
            description = "주문 목록을 조회하는 API 입니다. 무한스크롤로 구현되어 있습니다. " +
                    "size를 결정하고 이전 목록 조회는 page 값을 할당하고 cursor는 빼주세요, 다음 목록 조회는 cursor로 요청해주세요.",
            tags = {"ORDER-SERVICE"}
    )
    @GetMapping
    public ApiResponse<CursorPage<OrderCursorResponseVo>> getOrderList(Authentication authentication,
                                                                       @ModelAttribute OrderListFilterVo orderListFilterVo) {
        String memberUuid = authentication.getName();

        OrderListFilterDto orderListFilterDto = OrderListFilterDto.of(orderListFilterVo, memberUuid);

        CursorPage<OrderCursorResponseDto> response = orderService.getOrderList(orderListFilterDto);

        return ApiResponse.ok("주문 목록 조회 성공",
                orderService.getOrderList(OrderListFilterDto.of(orderListFilterVo, authentication.getName()))
                        .map(OrderCursorResponseDto::toVo)
        );
    }

    @Operation(
            summary = "주문 취소 API",
            description = "주문을 취소하는 API 입니다.",
            tags = {"ORDER-SERVICE"}
    )
    @PostMapping("/cancel")
    public ApiResponse<Void> cancelOrder(Authentication authentication,
                                         @RequestBody OrderCancelRequestVo orderCancelRequestVo) {

        orderService.cancelOrder(OrderCancelRequestDto.of(orderCancelRequestVo, authentication.getName()));

        return ApiResponse.ok("주문이 성공적으로 취소되었습니다.", null);
    }

    @Operation(
            summary = "주문 존재 여부 조회 API",
            description = "주문 존재 여부를 조회하는 API 입니다. 주문 코드로 주문이 존재하는지 확인합니다.",
            tags = {"ORDER-SERVICE"}
    )
    @GetMapping("/{orderCode}/exists")
    public ApiResponse<OrderExistsResponseVo> getOrderDetail(Authentication authentication,
                                                             @PathVariable Long orderCode) {
        return ApiResponse.ok("주문 존재 여부 조회 성공",
                orderService.existsOrder(OrderExistsRequestDto.of(orderCode, authentication.getName())).toVo()
        );
    }
}

