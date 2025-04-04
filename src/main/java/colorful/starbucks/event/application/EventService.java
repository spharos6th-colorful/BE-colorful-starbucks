package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventCreateRequestDto;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import colorful.starbucks.event.dto.request.EventUpdateRequestDto;
import colorful.starbucks.event.dto.response.EventDetailResponseDto;
import colorful.starbucks.event.dto.response.EventResponseDto;
import org.springframework.data.domain.Page;

public interface EventService {

    void createEvent(EventCreateRequestDto eventCreateRequestDto);

    void updateEvent(EventUpdateRequestDto eventUpdateRequestDto);

    Page<EventResponseDto> getEvents(EventFilterRequestDto eventFilterRequestDto);

    EventDetailResponseDto getEventDetail(String eventUuid);
}
