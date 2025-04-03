package colorful.starbucks.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberCoupon is a Querydsl query type for MemberCoupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCoupon extends EntityPathBase<MemberCoupon> {

    private static final long serialVersionUID = -1012395266L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberCoupon memberCoupon = new QMemberCoupon("memberCoupon");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final QCouponList couponList;

    public final BooleanPath couponUseState = createBoolean("couponUseState");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public QMemberCoupon(String variable) {
        this(MemberCoupon.class, forVariable(variable), INITS);
    }

    public QMemberCoupon(Path<? extends MemberCoupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberCoupon(PathMetadata metadata, PathInits inits) {
        this(MemberCoupon.class, metadata, inits);
    }

    public QMemberCoupon(Class<? extends MemberCoupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.couponList = inits.isInitialized("couponList") ? new QCouponList(forProperty("couponList")) : null;
    }

}

