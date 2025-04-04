package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventCreateRequestDto;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import colorful.starbucks.event.dto.response.EventResponseDto;
import colorful.starbucks.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Transactional
    @Override
    public void createEvent(EventCreateRequestDto eventCreateRequestDto) {
        eventRepository.save(eventCreateRequestDto.toEntity());
    }

    @Override
    public Page<EventResponseDto> getEvents(EventFilterRequestDto eventFilterRequestDto) {
        return eventRepository.getEvents(eventFilterRequestDto).map(EventResponseDto::from);
    }
}
