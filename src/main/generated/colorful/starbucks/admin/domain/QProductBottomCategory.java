package colorful.starbucks.admin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductBottomCategory is a Querydsl query type for ProductBottomCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductBottomCategory extends EntityPathBase<ProductBottomCategory> {

    private static final long serialVersionUID = 1250638825L;

    public static final QProductBottomCategory productBottomCategory = new QProductBottomCategory("productBottomCategory");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public QProductBottomCategory(String variable) {
        super(ProductBottomCategory.class, forVariable(variable));
    }

    public QProductBottomCategory(Path<? extends ProductBottomCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductBottomCategory(PathMetadata metadata) {
        super(ProductBottomCategory.class, metadata);
    }

}

