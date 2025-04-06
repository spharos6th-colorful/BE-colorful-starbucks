package colorful.starbucks.event.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품 코드")
    @Column(nullable = false)
    private Long productCode;

    @Comment("이벤트 UUID")
    @Column(nullable = false)
    private String eventUuid;

    @Builder
    private EventProduct(Long id,
                         Long productCode,
                         String eventUuid) {
        this.id = id;
        this.productCode = productCode;
        this.eventUuid = eventUuid;
    }
}
