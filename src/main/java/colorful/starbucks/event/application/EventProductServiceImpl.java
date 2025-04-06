package colorful.starbucks.event.application;

import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.infrastructure.EventProductRepository;
import lombok.RequiredArgsConstructor;
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
}
