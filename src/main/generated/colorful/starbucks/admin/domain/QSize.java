package colorful.starbucks.admin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSize is a Querydsl query type for Size
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSize extends EntityPathBase<Size> {

    private static final long serialVersionUID = -2067927280L;

    public static final QSize size = new QSize("size1");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath sizeName = createString("sizeName");

    public QSize(String variable) {
        super(Size.class, forVariable(variable));
    }

    public QSize(Path<? extends Size> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSize(PathMetadata metadata) {
        super(Size.class, metadata);
    }

}

