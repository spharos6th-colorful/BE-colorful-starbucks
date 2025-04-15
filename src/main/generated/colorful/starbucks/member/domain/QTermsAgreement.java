package colorful.starbucks.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTermsAgreement is a Querydsl query type for TermsAgreement
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTermsAgreement extends EntityPathBase<TermsAgreement> {

    private static final long serialVersionUID = 2129773525L;

    public static final QTermsAgreement termsAgreement = new QTermsAgreement("termsAgreement");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isAgreed = createBoolean("isAgreed");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final NumberPath<Long> termsId = createNumber("termsId", Long.class);

    public QTermsAgreement(String variable) {
        super(TermsAgreement.class, forVariable(variable));
    }

    public QTermsAgreement(Path<? extends TermsAgreement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTermsAgreement(PathMetadata metadata) {
        super(TermsAgreement.class, metadata);
    }

}

