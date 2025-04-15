package colorful.starbucks.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1845005440L;

    public static final QOrder order = new QOrder("order1");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath buyer = createString("buyer");

    public final StringPath couponName = createString("couponName");

    public final StringPath couponUuid = createString("couponUuid");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Integer> discountAmount = createNumber("discountAmount", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final BooleanPath isGift = createBoolean("isGift");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final EnumPath<OrderCancelReason> orderCancelReason = createEnum("orderCancelReason", OrderCancelReason.class);

    public final StringPath orderCancelReasonDetail = createString("orderCancelReasonDetail");

    public final NumberPath<Long> orderCode = createNumber("orderCode", Long.class);

    public final EnumPath<OrderStatus> orderStatus = createEnum("orderStatus", OrderStatus.class);

    public final NumberPath<Integer> totalAmount = createNumber("totalAmount", Integer.class);

    public final StringPath zoneCode = createString("zoneCode");

    public QOrder(String variable) {
        super(Order.class, forVariable(variable));
    }

    public QOrder(Path<? extends Order> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrder(PathMetadata metadata) {
        super(Order.class, metadata);
    }

}

