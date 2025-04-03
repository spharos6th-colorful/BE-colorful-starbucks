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

    public final NumberPath<Integer> afterDiscountPrice = createNumber("afterDiscountPrice", Integer.class);

    public final NumberPath<Integer> beforeDiscountPrice = createNumber("beforeDiscountPrice", Integer.class);

    public final NumberPath<Long> couponId = createNumber("couponId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final BooleanPath giftStatus = createBoolean("giftStatus");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath mainAddress = createString("mainAddress");

    public final StringPath memberUuid = createString("memberUuid");

    public final StringPath orderUuid = createString("orderUuid");

    public final BooleanPath payStatus = createBoolean("payStatus");

    public final StringPath postNumber = createString("postNumber");

    public final StringPath subAddress = createString("subAddress");

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

