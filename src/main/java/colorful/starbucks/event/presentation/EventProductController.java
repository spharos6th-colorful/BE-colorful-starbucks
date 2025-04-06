package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.event.application.EventProductService;
import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.request.EventProductFilterRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodeResponseDto;
import colorful.starbucks.event.vo.request.EventProductCreateRequestVo;
import colorful.starbucks.event.vo.request.EventProductFilterRequestVo;
import colorful.starbucks.event.vo.response.EventProductCodeResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event-products")
@RequiredArgsConstructor
public class EventProductController {

    private final EventProductService eventProductService;

    @PostMapping
    public ApiResponse<Void> createEventProduct(@RequestBody EventProductCreateRequestVo eventProductCreateRequestVo) {
        eventProductService.createEventProduct(EventProductCreateRequestDto.from(eventProductCreateRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "이벤트 상품 등록을 완료했습니다.",
                null);
    }

    @GetMapping
    public ApiResponse<PageResponse<EventProductCodeResponseVo>> getEventProductCodes(@ModelAttribute EventProductFilterRequestVo eventProductFilterRequestVo) {
        return ApiResponse.ok(
                "이벤트 상품 조회를 완료했습니다.",
                PageResponse.from(eventProductService.getEventProductCodes(EventProductFilterRequestDto.from(eventProductFilterRequestVo))
                        .map(EventProductCodeResponseDto::toVo)
                )
        );
    }

}
