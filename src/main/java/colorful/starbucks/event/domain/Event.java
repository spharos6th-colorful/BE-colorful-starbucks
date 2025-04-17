package colorful.starbucks.event.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이벤트 코드")
    @Column(nullable = false, unique = true)
    private String eventUuid;

    @Comment("이벤트 이름")
    @Column(nullable = false)
    private String title;

    @Comment("이벤트 설명")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Lob
    @Comment("이벤트 이미지")
    @Column(nullable = false)
    private String imageUrl;

    @Lob
    @Comment("이벤트 썸네일")
    @Column(nullable = false)
    private String thumbnailUrl;

    @Comment("이벤트 시작일")
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Comment("이벤트 종료일")
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Comment("이벤트 정책")
    @Column(nullable = false)
    private String policy;

    @Enumerated(EnumType.STRING)
    @Comment("이벤트 상태")
    @Column(nullable = false)
    private EventStatus status;

    @Builder
    private Event(Long id,
                  String eventUuid,
                  String title,
                  LocalDateTime startDate,
                  LocalDateTime endDate,
                  String description,
                  String imageUrl,
                  String thumbnailUrl,
                  String policy,
                  EventStatus status) {
        this.id = id;
        this.eventUuid = eventUuid;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.policy = policy;
        this.status = status;
    }
}
