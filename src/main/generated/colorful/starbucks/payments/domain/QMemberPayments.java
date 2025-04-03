package colorful.starbucks.payments.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberPayments is a Querydsl query type for MemberPayments
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberPayments extends EntityPathBase<MemberPayments> {

    private static final long serialVersionUID = 322450636L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberPayments memberPayments = new QMemberPayments("memberPayments");

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

    public final QPayments payments;

    public QMemberPayments(String variable) {
        this(MemberPayments.class, forVariable(variable), INITS);
    }

    public QMemberPayments(Path<? extends MemberPayments> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberPayments(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberPayments(PathMetadata metadata, PathInits inits) {
        this(MemberPayments.class, metadata, inits);
    }

    public QMemberPayments(Class<? extends MemberPayments> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.payments = inits.isInitialized("payments") ? new QPayments(forProperty("payments")) : null;
    }

}

