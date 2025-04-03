package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.EventCreateRequestDto;
import colorful.starbucks.event.dto.EventResponseDto;
import colorful.starbucks.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Transactional
    @Override
    public void createEvent(EventCreateRequestDto eventCreateRequestDto) {
        eventRepository.save(eventCreateRequestDto.toEntity());
    }

    @Override
    public Page<EventResponseDto> getEvents(Pageable pageable) {
        return eventRepository.getEvents(pageable).map(EventResponseDto::from);
    }
}
