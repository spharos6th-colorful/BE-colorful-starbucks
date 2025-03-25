package colorful.starbucks.event.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EventProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_product_id")
    private Long id;

    private String productCode;

    private String eventUuid;

    @Builder
    public EventProduct(Long id,String productCode, String eventUuid) {
        this.id = id;
        this.productCode = productCode;
        this.eventUuid = eventUuid;
    }
}
