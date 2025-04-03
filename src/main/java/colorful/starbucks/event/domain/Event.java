package colorful.starbucks.event.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("이벤트 코드")
    @Column(nullable = false)
    private String eventUuid;

    @Comment("이벤트 이름")
    @Column(nullable = false)
    private String eventName;

    @Comment("이벤트 시작일")
    @Column(nullable = false)
    private LocalDateTime eventStartDate;

    @Comment("이벤트 종료일")
    @Column(nullable = false)
    private LocalDateTime eventEndDate;

    @Comment("이벤트 설명")
    @Column(columnDefinition = "TEXT")
    private String eventDescription;

    @Lob
    @Comment("이벤트 이미지")
    @Column(nullable = false)
    private String eventImageUrl;

    @Lob
    @Comment("이벤트 썸네일")
    @Column(nullable = false)
    private String eventThumbnailUrl;

    @Comment("이벤트 정책")
    @Column(nullable = false)
    private String eventPolicy;

    @Enumerated(EnumType.STRING)
    @Comment("이벤트 상태")
    @Column(nullable = false)
    private EventStatus eventStatus;

    @Builder
    private Event(Long id,
                  String eventUuid,
                  String eventName,
                  LocalDateTime eventStartDate,
                  LocalDateTime eventEndDate,
                  String eventDescription,
                  String eventImageUrl,
                  String eventThumbnailUrl,
                  String eventPolicy,
                  EventStatus eventStatus) {
        this.id = id;
        this.eventUuid = eventUuid;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventDescription = eventDescription;
        this.eventImageUrl = eventImageUrl;
        this.eventThumbnailUrl = eventThumbnailUrl;
        this.eventPolicy = eventPolicy;
        this.eventStatus = eventStatus;
    }
}
