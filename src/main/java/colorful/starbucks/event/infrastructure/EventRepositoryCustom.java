package colorful.starbucks.event.infrastructure;

import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import org.springframework.data.domain.Page;

public interface EventRepositoryCustom {

    Page<Event> getEvents(EventFilterRequestDto eventFilterRequestDto);
}
