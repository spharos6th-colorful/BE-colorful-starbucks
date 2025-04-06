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

    @Lob
    @Comment("상품 썸네일 URL")
    @Column(nullable = false)
    private String productThumbnailUrl;

    @Comment("상품 이름")
    @Column(nullable = false)
    private String productName;

    @Comment("상품 가격")
    @Column(nullable = false)
    private int price;

    @Builder
    private EventProduct(Long id,
                         Long productCode,
                         String eventUuid,
                         String productThumbnailUrl,
                         String productName,
                         int price) {
        this.id = id;
        this.productCode = productCode;
        this.eventUuid = eventUuid;
        this.productThumbnailUrl = productThumbnailUrl;
        this.productName = productName;
        this.price = price;
    }
}
