package colorful.starbucks.event.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.event.domain.Event;
import colorful.starbucks.event.dto.request.EventCreateRequestDto;
import colorful.starbucks.event.dto.request.EventFilterRequestDto;
import colorful.starbucks.event.dto.request.EventUpdateRequestDto;
import colorful.starbucks.event.dto.response.EventDetailResponseDto;
import colorful.starbucks.event.dto.response.EventResponseDto;
import colorful.starbucks.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Transactional
    @Override
    public void updateEvent(EventUpdateRequestDto eventUpdateRequestDto) {
        Event event = eventRepository.findByEventUuid(eventUpdateRequestDto.getEventUuid())
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND));

        event.update(eventUpdateRequestDto.toEntity());
    }

    @Override
    public Page<EventResponseDto> getEvents(EventFilterRequestDto eventFilterRequestDto) {
        return eventRepository.getEvents(eventFilterRequestDto).map(EventResponseDto::from);
    }

    @Override
    public EventDetailResponseDto getEventDetail(String eventUuid) {
        return EventDetailResponseDto.from(eventRepository.findByEventUuid(eventUuid)
                .orElseThrow(() -> new BaseException(ResponseStatus.RESOURCE_NOT_FOUND))
        );
    }

    @Transactional
    @Override
    public void deleteEvent(String eventUuid) {
         eventRepository.deleteByEventUuid(eventUuid);
    }
}
