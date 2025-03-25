package colorful.starbucks.orderdetail.domain;

import colorful.starbucks.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private String productUuid;

    private String productDetailUuid;

    private String productName;

    private int size;

    private String color;

    private int quantity;

    private int price;

    private String carvingContent;

    private Long categoryId;


    @Builder
    private OrderDetail(Long id,
                        Order order,
                        String productUuid,
                        String productDetailUuid,
                        String productName,
                        int size,
                        String color,
                        int quantity,
                        int price,
                        String carvingContent,
                        Long categoryId) {
        this.id = id;
        this.order = order;
        this.productUuid = productUuid;
        this.productDetailUuid = productDetailUuid;
        this.productName = productName;
        this.size = size;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.carvingContent = carvingContent;
        this.categoryId = categoryId;
    }

}
