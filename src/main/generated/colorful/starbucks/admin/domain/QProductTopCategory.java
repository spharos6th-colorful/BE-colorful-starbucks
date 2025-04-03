package colorful.starbucks.admin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductTopCategory is a Querydsl query type for ProductTopCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductTopCategory extends EntityPathBase<ProductTopCategory> {

    private static final long serialVersionUID = -2008971821L;

    public static final QProductTopCategory productTopCategory = new QProductTopCategory("productTopCategory");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath categoryName = createString("categoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public QProductTopCategory(String variable) {
        super(ProductTopCategory.class, forVariable(variable));
    }

    public QProductTopCategory(Path<? extends ProductTopCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductTopCategory(PathMetadata metadata) {
        super(ProductTopCategory.class, metadata);
    }

}

