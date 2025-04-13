package colorful.starbucks.coupon.application;

import colorful.starbucks.common.exception.BaseException;
import colorful.starbucks.common.response.ResponseStatus;
import colorful.starbucks.coupon.dto.request.EventCouponMappingCreateRequestDto;
import colorful.starbucks.coupon.dto.request.EventCouponUuidRequestDto;
import colorful.starbucks.coupon.dto.response.EventCouponUuidResponseDto;
import colorful.starbucks.coupon.infrastructure.EventCouponMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventCouponMappingServiceImpl implements EventCouponMappingService {

    private final EventCouponMappingRepository eventCouponMappingRepository;

    @Transactional
    @Override
    public void createEventCouponMapping(EventCouponMappingCreateRequestDto eventCouponMappingCreateRequestDto) {

        try {
            eventCouponMappingRepository.save(eventCouponMappingCreateRequestDto.toEntity());
        } catch (DataIntegrityViolationException e) {
            throw new BaseException(ResponseStatus.CONFLICT_REQUEST);
        }
    }

    @Override
    public Page<EventCouponUuidResponseDto> getEventCouponMappingList(EventCouponUuidRequestDto eventCouponUuidRequestDto) {
        return eventCouponMappingRepository.findAllByEventUuid(eventCouponUuidRequestDto.getEventUuid(), eventCouponUuidRequestDto.getPageable())
                .map(EventCouponUuidResponseDto::from);
    }
}
