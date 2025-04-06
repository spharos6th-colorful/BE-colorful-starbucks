package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.event.application.EventProductService;
import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.vo.request.EventProductCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/event-products")
@RequiredArgsConstructor
public class EventProductController {

    private final EventProductService eventProductService;

    @PostMapping
    public ApiResponse<Void> createEventProduct(@RequestBody EventProductCreateRequestVo eventProductCreateRequestVo) {
        eventProductService.createEventProduct(EventProductCreateRequestDto.from(eventProductCreateRequestVo));
        return ApiResponse.ok("이벤트 상품 등록을 완료했습니다.", null);
    }
}
