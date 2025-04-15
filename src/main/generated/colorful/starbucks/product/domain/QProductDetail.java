package colorful.starbucks.product.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductDetail is a Querydsl query type for ProductDetail
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProductDetail extends EntityPathBase<ProductDetail> {

    private static final long serialVersionUID = -1481676975L;

    public static final QProductDetail productDetail = new QProductDetail("productDetail");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final NumberPath<Long> colorId = createNumber("colorId", Long.class);

    public final StringPath colorName = createString("colorName");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Integer> discountPrice = createNumber("discountPrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> inventoryQuantity = createNumber("inventoryQuantity", Integer.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> productCode = createNumber("productCode", Long.class);

    public final NumberPath<Long> productDetailCode = createNumber("productDetailCode", Long.class);

    public final StringPath productDetailThumbnailUrl = createString("productDetailThumbnailUrl");

    public final NumberPath<Long> sizeId = createNumber("sizeId", Long.class);

    public final StringPath sizeName = createString("sizeName");

    public QProductDetail(String variable) {
        super(ProductDetail.class, forVariable(variable));
    }

    public QProductDetail(Path<? extends ProductDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductDetail(PathMetadata metadata) {
        super(ProductDetail.class, metadata);
    }

}

