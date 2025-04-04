package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.event.application.EventService;
import colorful.starbucks.event.dto.request.EventCreateRequestDto;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import colorful.starbucks.event.dto.response.EventResponseDto;
import colorful.starbucks.event.vo.request.EventCreateRequestVo;
import colorful.starbucks.event.vo.request.EventFilterRequestVo;
import colorful.starbucks.event.vo.response.EventDetailResponseVo;
import colorful.starbucks.event.vo.response.EventResponseVo;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse<PageResponse<EventResponseVo>> getEvents(@ModelAttribute EventFilterRequestVo eventFilterRequestVo) {
        return ApiResponse.ok(
                "이벤트 조회를 완료했습니다.",
                PageResponse.from(eventService.getEvents(EventFilterRequestDto.from(eventFilterRequestVo))
                        .map(EventResponseDto::toVo)
                )
        );
    }

    @GetMapping("/{eventUuid}")
    public ApiResponse<EventDetailResponseVo> getEventDetail(@PathVariable String eventUuid) {
        return ApiResponse.ok(
                "이벤트 상세 조회를 완료했습니다.",
                eventService.getEventDetail(eventUuid).toVo()
        );
    }

    @DeleteMapping("/{eventUuid}")
    public ApiResponse<Void> deleteEvent(@PathVariable String eventUuid) {
        eventService.deleteEvent(eventUuid);
        return ApiResponse.of(HttpStatus.NO_CONTENT,
                "이벤트 삭제를 완료했습니다.",
                null
        );
    }
}