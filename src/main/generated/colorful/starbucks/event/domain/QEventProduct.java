package colorful.starbucks.event.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEventProduct is a Querydsl query type for EventProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventProduct extends EntityPathBase<EventProduct> {

    private static final long serialVersionUID = -1237377841L;

    public static final QEventProduct eventProduct = new QEventProduct("eventProduct");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath eventUuid = createString("eventUuid");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath productCode = createString("productCode");

    public QEventProduct(String variable) {
        super(EventProduct.class, forVariable(variable));
    }

    public QEventProduct(Path<? extends EventProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEventProduct(PathMetadata metadata) {
        super(EventProduct.class, metadata);
    }

}

