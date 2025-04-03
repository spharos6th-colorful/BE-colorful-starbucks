package colorful.starbucks.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -798603264L;

    public static final QEvent event = new QEvent("event");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath eventDescription = createString("eventDescription");

    public final DateTimePath<java.time.LocalDateTime> eventEndDate = createDateTime("eventEndDate", java.time.LocalDateTime.class);

    public final StringPath eventImage = createString("eventImage");

    public final StringPath eventName = createString("eventName");

    public final StringPath eventPolicy = createString("eventPolicy");

    public final BooleanPath eventPostStatus = createBoolean("eventPostStatus");

    public final DateTimePath<java.time.LocalDateTime> eventStartDate = createDateTime("eventStartDate", java.time.LocalDateTime.class);

    public final StringPath eventThumbnail = createString("eventThumbnail");

    public final StringPath eventUuid = createString("eventUuid");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

