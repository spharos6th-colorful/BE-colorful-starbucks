package colorful.starbucks.order.domain;

import colorful.starbucks.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String productCode;

    private String productDetailCode;

    private String productName;

    private int size;

    private String color;

    private int quantity;

    private int price;

    private String carvingContent;

    private String categoryName;

    private Boolean carving;

    @Builder
    private OrderDetail(Long id,
                       Order order,
                       String productCode,
                       String productDetailCode,
                       String productName,
                       int size,
                       String color,
                       int quantity,
                       int price,
                       String carvingContent,
                       String categoryName,
                       Boolean carving) {
        this.id = id;
        this.order = order;
        this.productCode = productCode;
        this.productDetailCode = productDetailCode;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.carvingContent = carvingContent;
        this.categoryName = categoryName;
        this.carving = carving;
    }
}


