package colorful.starbucks.payments.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPaymentsList is a Querydsl query type for PaymentsList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentsList extends EntityPathBase<PaymentsList> {

    private static final long serialVersionUID = 609893264L;

    public static final QPaymentsList paymentsList = new QPaymentsList("paymentsList");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final StringPath orderUuid = createString("orderUuid");

    public final StringPath paymentsNumber = createString("paymentsNumber");

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QPaymentsList(String variable) {
        super(PaymentsList.class, forVariable(variable));
    }

    public QPaymentsList(Path<? extends PaymentsList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPaymentsList(PathMetadata metadata) {
        super(PaymentsList.class, metadata);
    }

}

