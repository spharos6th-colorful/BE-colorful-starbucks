package colorful.starbucks.event.application;

import colorful.starbucks.common.util.CursorPage;
import colorful.starbucks.event.dto.request.EventProductCreateRequestDto;
import colorful.starbucks.event.dto.request.EventProductCodesRequestDto;
import colorful.starbucks.event.dto.response.EventProductCodesResponseDto;
import colorful.starbucks.event.infrastructure.EventProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventProductServiceImpl implements EventProductService {

    private final EventProductRepository eventProductRepository;

    @Transactional
    @Override
    public void createEventProduct(List<EventProductCreateRequestDto> eventProductCreateRequestDtos) {
        eventProductRepository.saveAll(eventProductCreateRequestDtos.stream()
                .map(EventProductCreateRequestDto::toEntity)
                .toList());
    }

    @Override
    public CursorPage<EventProductCodesResponseDto> getEventProductCodes(EventProductCodesRequestDto eventProductCodesRequestDto) {
        return eventProductRepository.getEventProductCodesByEventUuid(eventProductCodesRequestDto)
                .map(EventProductCodesResponseDto::from);
    }
}
