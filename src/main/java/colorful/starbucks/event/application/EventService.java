package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.EventCreateRequestDto;

public interface EventService {

    void createEvent(EventCreateRequestDto eventCreateRequestDto);
}
