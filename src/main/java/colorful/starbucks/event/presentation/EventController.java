package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.event.application.EventService;
import colorful.starbucks.event.dto.EventCreateRequestDto;
import colorful.starbucks.event.dto.EventResponseDto;
import colorful.starbucks.event.vo.EventCreateRequestVo;
import colorful.starbucks.event.vo.EventResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ApiResponse<Void> createEvent(@RequestBody EventCreateRequestVo eventCreateRequestVo) {

        eventService.createEvent(EventCreateRequestDto.from(eventCreateRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "이벤트 등록을 완료했습니다.",
                null
        );
    }

    @GetMapping
    public ApiResponse<PageResponse<EventResponseVo>> getEvents(@PageableDefault(size = 3) Pageable pageable) {
        return ApiResponse.ok(
                "이벤트 조회를 완료했습니다.",
                PageResponse.from(eventService.getEvents(pageable).map(EventResponseDto::toVo))
        );
    }
}
