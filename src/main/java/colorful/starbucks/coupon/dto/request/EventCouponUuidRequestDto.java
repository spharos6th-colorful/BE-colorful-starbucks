package colorful.starbucks.coupon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor
public class EventCouponUuidRequestDto {

    private String eventUuid;
    private Pageable pageable;

    @Builder
    private EventCouponUuidRequestDto(String eventUuid, Pageable pageable) {
        this.eventUuid = eventUuid;
        this.pageable = pageable;
    }

    public static EventCouponUuidRequestDto of(String eventUuid, Pageable pageable) {
        return EventCouponUuidRequestDto.builder()
                .eventUuid(eventUuid)
                .pageable(pageable)
                .build();
    }
}
