package colorful.starbucks.delivery.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDeliveryAddress is a Querydsl query type for DeliveryAddress
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeliveryAddress extends EntityPathBase<DeliveryAddress> {

    private static final long serialVersionUID = -2093962124L;

    public static final QDeliveryAddress deliveryAddress = new QDeliveryAddress("deliveryAddress");

    public final colorful.starbucks.common.entity.QBaseEntity _super = new colorful.starbucks.common.entity.QBaseEntity(this);

    public final StringPath address = createString("address");

    public final StringPath addressNickname = createString("addressNickname");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailAddress = createString("detailAddress");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDefaultAddress = createBoolean("isDefaultAddress");

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedAt = _super.lastModifiedAt;

    public final StringPath memberAddressUuid = createString("memberAddressUuid");

    public final StringPath memberUuid = createString("memberUuid");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath receiverName = createString("receiverName");

    public final StringPath zoneCode = createString("zoneCode");

    public QDeliveryAddress(String variable) {
        super(DeliveryAddress.class, forVariable(variable));
    }

    public QDeliveryAddress(Path<? extends DeliveryAddress> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDeliveryAddress(PathMetadata metadata) {
        super(DeliveryAddress.class, metadata);
    }

}

