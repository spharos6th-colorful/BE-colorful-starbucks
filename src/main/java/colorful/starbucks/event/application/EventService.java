package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.EventCreateRequestDto;
import colorful.starbucks.event.dto.EventResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    void createEvent(EventCreateRequestDto eventCreateRequestDto);

    Page<EventResponseDto> getEvents(Pageable pageable);
}
