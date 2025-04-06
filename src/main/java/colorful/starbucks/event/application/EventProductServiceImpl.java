package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.request.EventProductFilterRequestDto;
import colorful.starbucks.event.dto.response.EventProductResponseDto;
import colorful.starbucks.event.infrastructure.EventProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventProductServiceImpl implements EventProductService {

    private final EventProductRepository eventProductRepository;

    @Transactional
    @Override
    public void createEventProduct(EventProductCreateRequestDto eventProductCreateRequestDto) {
        eventProductRepository.save(eventProductCreateRequestDto.toEntity());
    }

    @Override
    public Page<EventProductResponseDto> getEventProducts(EventProductFilterRequestDto eventProductFilterRequestDto) {
        return eventProductRepository.getEventProducts(
                        eventProductFilterRequestDto.getEventUuid(),
                        eventProductFilterRequestDto.getPage(),
                        eventProductFilterRequestDto.getSize())
                .map(EventProductResponseDto::from);
    }
}
