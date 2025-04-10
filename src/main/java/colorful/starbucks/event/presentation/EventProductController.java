package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.event.application.EventProductService;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodesResponseDto;
import colorful.starbucks.event.vo.request.EventProductCodesRequestVo;
import colorful.starbucks.event.vo.request.EventProductCreateRequestVo;
import colorful.starbucks.event.vo.response.EventProductCodesResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event-products")
@RequiredArgsConstructor
public class EventProductController {

    private final EventProductService eventProductService;

    @PostMapping
    public ApiResponse<Void> createEventProduct(@RequestBody List<EventProductCreateRequestVo> eventProductCreateRequestVos) {
        eventProductService.createEventProduct(eventProductCreateRequestVos.stream().map(EventProductCreateRequestDto::from).toList());
        return ApiResponse.of(HttpStatus.CREATED,
                "이벤트 상품 등록을 완료했습니다.",
                null);
    }

    @GetMapping
    public ApiResponse<CursorPage<EventProductCodesResponseVo>> getEventProductCodes(@ModelAttribute EventProductCodesRequestVo eventProductCodesRequestVo) {
;
        return ApiResponse.ok(
                "이벤트 상품 조회를 완료했습니다.",
                eventProductService.getEventProductCodes(EventProductCodesRequestDto.from(eventProductCodesRequestVo))
                        .map(EventProductCodesResponseDto::toVo)
        );
    }

}
