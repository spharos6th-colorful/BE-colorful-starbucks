package colorful.starbucks.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberCoupon is a Querydsl query type for MemberCoupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberCoupon extends EntityPathBase<MemberCoupon> {

    private static final long serialVersionUID = -1012395266L;

    public static final QMemberCoupon memberCoupon = new QMemberCoupon("memberCoupon");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath couponUuid = createString("couponUuid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final BooleanPath isUsed = createBoolean("isUsed");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final DateTimePath<java.time.LocalDateTime> usedAt = createDateTime("usedAt", java.time.LocalDateTime.class);

    public final StringPath usedOrderCode = createString("usedOrderCode");

    public QMemberCoupon(String variable) {
        super(MemberCoupon.class, forVariable(variable));
    }

    public QMemberCoupon(Path<? extends MemberCoupon> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCoupon(PathMetadata metadata) {
        super(MemberCoupon.class, metadata);
    }

}

