package colorful.starbucks.summary.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class MemberOrderSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 주문 집계 ID")
    private Long id;

    @Column(nullable = false)
    @Comment("회원 UUID")
    private String memberUuid;

    @Column(nullable = false)
    @Comment("회원 이름")
    private String memberName;

    @Column(nullable = false)
    @Comment("주문완료된 총 금액")
    private Integer totalPaidAmount;

    @Column(nullable = false)
    @Comment("주문완료된 총 수량")
    private Integer totalOrderCount;

    @Column(nullable = false)
    @Comment("마지막 주문일자")
    private LocalDateTime lastOrderDate;

    @LastModifiedDate
    @Column(nullable = false)
    @Comment("마지막 집계 일자")
    private LocalDateTime lastUpdateDate;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Comment("집계 테이블 생성일자")
    private LocalDateTime createdAt;

    @Builder
    private MemberOrderSummary(
            String memberUuid,
            String memberName,
            Integer totalPaidAmount,
            Integer totalOrderCount,
            LocalDateTime lastOrderDate
    ) {
        this.memberUuid = memberUuid;
        this.memberName = memberName;
        this.totalPaidAmount = totalPaidAmount;
        this.totalOrderCount = totalOrderCount;
        this.lastOrderDate = lastOrderDate;
    }
}
