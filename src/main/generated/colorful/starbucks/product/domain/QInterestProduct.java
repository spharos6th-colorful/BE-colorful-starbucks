package colorful.starbucks.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QInterestProduct is a Querydsl query type for InterestProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestProduct extends EntityPathBase<InterestProduct> {

    private static final long serialVersionUID = -477366442L;

    public static final QInterestProduct interestProduct = new QInterestProduct("interestProduct");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productCode = createString("productCode");

    public final StringPath productName = createString("productName");

    public final StringPath productThumbnailUrl = createString("productThumbnailUrl");

    public QInterestProduct(String variable) {
        super(InterestProduct.class, forVariable(variable));
    }

    public QInterestProduct(Path<? extends InterestProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QInterestProduct(PathMetadata metadata) {
        super(InterestProduct.class, metadata);
    }

}

