package colorful.starbucks.payments.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentHistory is a Querydsl query type for PaymentHistory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentHistory extends EntityPathBase<PaymentHistory> {

    private static final long serialVersionUID = -971536237L;

    public static final QPaymentHistory paymentHistory = new QPaymentHistory("paymentHistory");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath approvedAt = createString("approvedAt");

    public final StringPath canceledAt = createString("canceledAt");

    public final StringPath cancelReason = createString("cancelReason");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final NumberPath<Long> orderCode = createNumber("orderCode", Long.class);

    public final StringPath paymentKey = createString("paymentKey");

    public final StringPath paymentsNumber = createString("paymentsNumber");

    public final EnumPath<PaymentStatus> paymentStatus = createEnum("paymentStatus", PaymentStatus.class);

    public final EnumPath<PaymentsType> paymentsType = createEnum("paymentsType", PaymentsType.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QPaymentHistory(String variable) {
        super(PaymentHistory.class, forVariable(variable));
    }

    public QPaymentHistory(Path<? extends PaymentHistory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentHistory(PathMetadata metadata) {
        super(PaymentHistory.class, metadata);
    }

}

