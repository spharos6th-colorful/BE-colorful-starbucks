package colorful.starbucks.order.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.order.application.OrderDetailService;
import colorful.starbucks.order.dto.OrderDetailFilterDto;
import colorful.starbucks.order.dto.response.OrderDetailResponseDto;
import colorful.starbucks.order.vo.OrderDetailFilterVo;
import colorful.starbucks.order.vo.response.OrderDetailResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Operation(
            summary = "주문 상세 목록 조회 API",
            description = "주문 상세 목록을 조회하는 API 입니다.",
            tags = {"ORDER-SERVICE"}
    )

    @GetMapping("/{orderId}/details")
    public ApiResponse<CursorPage<OrderDetailResponseVo>> getOrderDetailList(@PathVariable Long orderId,
                                                                             @ModelAttribute OrderDetailFilterVo orderDetailFilterVo
    ) {

        return ApiResponse.ok("주문 상세 목록 조회 성공",
                orderDetailService.getOrderDetailList(OrderDetailFilterDto.of(orderDetailFilterVo, orderId))
                        .map(OrderDetailResponseDto::toVo));

    }
}
