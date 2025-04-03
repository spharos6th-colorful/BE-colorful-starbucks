package colorful.starbucks.event.presentation;

import colorful.starbucks.common.response.ApiResponse;
import colorful.starbucks.event.application.EventService;
import colorful.starbucks.event.dto.EventCreateRequestDto;
import colorful.starbucks.event.vo.EventCreateRequestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
