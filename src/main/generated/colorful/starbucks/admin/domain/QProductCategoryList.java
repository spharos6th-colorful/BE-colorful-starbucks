package colorful.starbucks.admin.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductCategoryList is a Querydsl query type for ProductCategoryList
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductCategoryList extends EntityPathBase<ProductCategoryList> {

    private static final long serialVersionUID = -658344356L;

    public static final QProductCategoryList productCategoryList = new QProductCategoryList("productCategoryList");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final NumberPath<Long> bottomCategoryId = createNumber("bottomCategoryId", Long.class);

    public final StringPath bottomCategoryName = createString("bottomCategoryName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> productCode = createNumber("productCode", Long.class);

    public final NumberPath<Long> topCategoryId = createNumber("topCategoryId", Long.class);

    public final StringPath topCategoryName = createString("topCategoryName");

    public QProductCategoryList(String variable) {
        super(ProductCategoryList.class, forVariable(variable));
    }

    public QProductCategoryList(Path<? extends ProductCategoryList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductCategoryList(PathMetadata metadata) {
        super(ProductCategoryList.class, metadata);
    }

}

