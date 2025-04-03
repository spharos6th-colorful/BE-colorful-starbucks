package colorful.starbucks.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponList is a Querydsl query type for CouponList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCouponList extends EntityPathBase<CouponList> {

    private static final long serialVersionUID = 1512007938L;

    public static final QCouponList couponList = new QCouponList("couponList");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final DateTimePath<java.time.LocalDateTime> couponEndDate = createDateTime("couponEndDate", java.time.LocalDateTime.class);

    public final StringPath couponUuid = createString("couponUuid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> endQuantity = createNumber("endQuantity", Integer.class);

    public final StringPath eventUuid = createString("eventUuid");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public QCouponList(String variable) {
        super(CouponList.class, forVariable(variable));
    }

    public QCouponList(Path<? extends CouponList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponList(PathMetadata metadata) {
        super(CouponList.class, metadata);
    }

}

