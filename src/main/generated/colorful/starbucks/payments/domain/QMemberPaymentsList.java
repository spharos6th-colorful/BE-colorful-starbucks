package colorful.starbucks.payments.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberPaymentsList is a Querydsl query type for MemberPaymentsList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPaymentsList extends EntityPathBase<MemberPaymentsList> {

    private static final long serialVersionUID = -1621290102L;

    public static final QMemberPaymentsList memberPaymentsList = new QMemberPaymentsList("memberPaymentsList");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath cardNumber = createString("cardNumber");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public QMemberPaymentsList(String variable) {
        super(MemberPaymentsList.class, forVariable(variable));
    }

    public QMemberPaymentsList(Path<? extends MemberPaymentsList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberPaymentsList(PathMetadata metadata) {
        super(MemberPaymentsList.class, metadata);
    }

}

