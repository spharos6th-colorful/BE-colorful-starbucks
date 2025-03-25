package colorful.starbucks.event.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private String eventUuid;

    private String eventName;

    private LocalDateTime eventStartDate;

    private LocalDateTime eventEndDate;

    private String eventDescription;

    private String eventImage;

    private String eventPolicy;

    private Boolean eventPostStatus;

    private String eventThumbnail;


    @Builder
    private Event(Long id,
                  String eventUuid,
                  String eventName,
                  LocalDateTime eventStartDate,
                  LocalDateTime eventEndDate,
                  String eventImage,
                  String eventPolicy,
                  Boolean eventPostStatus,
                  String eventThumbnail) {
        this.id = id;
        this.eventUuid = eventUuid;
        this.eventName = eventName;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = eventEndDate;
        this.eventDescription = eventDescription;
        this.eventImage = eventImage;
        this.eventPolicy = eventPolicy;
        this.eventPostStatus = eventPostStatus;
        this.eventThumbnail = eventThumbnail;
    }

}
