package colorful.starbucks.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventCouponMapping is a Querydsl query type for EventCouponMapping
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventCouponMapping extends EntityPathBase<EventCouponMapping> {

    private static final long serialVersionUID = -852772244L;

    public static final QEventCouponMapping eventCouponMapping = new QEventCouponMapping("eventCouponMapping");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath couponUuid = createString("couponUuid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath eventUuid = createString("eventUuid");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public QEventCouponMapping(String variable) {
        super(EventCouponMapping.class, forVariable(variable));
    }

    public QEventCouponMapping(Path<? extends EventCouponMapping> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventCouponMapping(PathMetadata metadata) {
        super(EventCouponMapping.class, metadata);
    }

}

