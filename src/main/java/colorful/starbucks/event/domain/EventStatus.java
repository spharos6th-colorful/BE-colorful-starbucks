package colorful.starbucks.event.domain;

public enum EventStatus {

    ONGOING("진행중"),
    UPCOMING("예정"),
    ENDED("종료")
    ;

    private final String description;

    EventStatus(String description) {
        this.description = description;
    }
}
