package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.common.util.PageResponse;
import colorful.starbucks.event.application.EventService;
import colorful.starbucks.event.dto.request.EventCreateRequestDto;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import colorful.starbucks.event.dto.request.EventUpdateRequestDto;
import colorful.starbucks.event.dto.response.EventResponseDto;
import colorful.starbucks.event.vo.request.EventCreateRequestVo;
import colorful.starbucks.event.vo.request.EventFilterRequestVo;
import colorful.starbucks.event.vo.response.EventDetailResponseVo;
import colorful.starbucks.event.vo.response.EventResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @Operation(
            summary = "이벤트 등록 API",
            description = "이벤트 등록을 위한 API 입니다.",
            tags = {"EVENT-SERVICE"}
    )
    @PostMapping
    public ApiResponse<Void> createEvent(@RequestBody EventCreateRequestVo eventCreateRequestVo) {

        eventService.createEvent(EventCreateRequestDto.from(eventCreateRequestVo));
        return ApiResponse.of(HttpStatus.CREATED,
                "이벤트 등록을 완료했습니다.",
                null
        );
    }

    @Operation(
            summary = "이벤트 수정 API",
            description = "이벤트 수정을 위한 API 입니다.",
            tags = {"EVENT-SERVICE"}
    )
    @PutMapping("/{eventUuid}")
    public ApiResponse<Void> updateEvent(@PathVariable String eventUuid,
                                         @RequestBody EventCreateRequestVo eventCreateRequestVo) {
        eventService.updateEvent(EventUpdateRequestDto.of(eventUuid, eventCreateRequestVo));
        return ApiResponse.ok(
                "이벤트 수정을 완료했습니다.",
                null
        );
    }

    @Operation(
            summary = "이벤트 목록 조회 API",
            description = "이벤트 목록을 조회하는 API 입니다.",
            tags = {"EVENT-SERVICE"}
    )
    @GetMapping
    public ApiResponse<PageResponse<EventResponseVo>> getEvents(@ModelAttribute EventFilterRequestVo eventFilterRequestVo) {

        return ApiResponse.ok(
                "이벤트 조회를 완료했습니다.",
                PageResponse.from(eventService.getEvents(EventFilterRequestDto.from(eventFilterRequestVo))
                        .map(EventResponseDto::toVo)
                )
        );
    }

    @Operation(
            summary = "이벤트 상세 조회 API",
            description = "이벤트 uuid로 이벤트를 조회하는 API 입니다.",
            tags = {"EVENT-SERVICE"}
    )
    @GetMapping("/{eventUuid}")
    public ApiResponse<EventDetailResponseVo> getEventDetail(@PathVariable String eventUuid) {
        return ApiResponse.ok(
                "이벤트 상세 조회를 완료했습니다.",
                eventService.getEventDetail(eventUuid).toVo()
        );
    }

    @Operation(
            summary = "이벤트 삭제 API",
            description = "이벤트 uuid로 이벤트를 삭제하는 API 입니다.",
            tags = {"EVENT-SERVICE"}
    )
    @DeleteMapping("/{eventUuid}")
    public ApiResponse<Void> deleteEvent(@PathVariable String eventUuid) {
        eventService.deleteEvent(eventUuid);
        return ApiResponse.of(HttpStatus.NO_CONTENT,
                "이벤트 삭제를 완료했습니다.",
                null
        );
    }
}