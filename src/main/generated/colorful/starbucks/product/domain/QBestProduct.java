package colorful.starbucks.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBestProduct is a Querydsl query type for BestProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBestProduct extends EntityPathBase<BestProduct> {

    private static final long serialVersionUID = 226395100L;

    public static final QBestProduct bestProduct = new QBestProduct("bestProduct");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final NumberPath<Long> productCode = createNumber("productCode", Long.class);

    public final NumberPath<Integer> productRank = createNumber("productRank", Integer.class);

    public final NumberPath<Integer> totalQuantity = createNumber("totalQuantity", Integer.class);

    public QBestProduct(String variable) {
        super(BestProduct.class, forVariable(variable));
    }

    public QBestProduct(Path<? extends BestProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBestProduct(PathMetadata metadata) {
        super(BestProduct.class, metadata);
    }

}

