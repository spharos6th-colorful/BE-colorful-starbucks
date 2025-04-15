package colorful.starbucks.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCart is a Querydsl query type for Cart
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCart extends EntityPathBase<Cart> {

    private static final long serialVersionUID = -227607624L;

    public static final QCart cart = new QCart("cart");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath carvingContent = createString("carvingContent");

    public final BooleanPath checked = createBoolean("checked");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberUuid = createString("memberUuid");

    public final NumberPath<Long> productCode = createNumber("productCode", Long.class);

    public final NumberPath<Long> productDetailCode = createNumber("productDetailCode", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QCart(String variable) {
        super(Cart.class, forVariable(variable));
    }

    public QCart(Path<? extends Cart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCart(PathMetadata metadata) {
        super(Cart.class, metadata);
    }

}

